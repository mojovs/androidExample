package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String newId;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //按键: 添加按钮
        Button btn_addData = (Button) findViewById(R.id.add_data);
        btn_addData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //添加数据
                Uri uri = Uri.parse("content://com.example.mydatabasehelper" +
                        ".provider/book");
                ContentValues values = new ContentValues();
                values.put("name","哈哈");
                values.put("author","JK");
                values.put("price",10);
                //添加到provider
                if(null ==getContentResolver())
                {
                    Log.d(TAG, "获取不到resolver");
                    return;
                }

                Uri newUri = getContentResolver().insert(uri,values);

                //content://com.example.mydatabasehelper.provider/book
                //插入一个item
                newId = newUri.getPathSegments().get(1);
            }
        });

        //按钮: 查询数据
        Button btn_queryData =(Button) findViewById(R.id.query_data);
        btn_queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //先获取游标
                Uri uri = Uri.parse("content://com.example.mydatabasehelper" +
                        ".provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null
                        ,null,null);
                if(cursor != null)
                {
                    while (cursor.moveToNext())
                    {
                        String name = cursor.getString(cursor.getColumnIndex(
                                "name"));
                        String author= cursor.getString(cursor.getColumnIndex(
                                "author"));
                        int pages= cursor.getInt(cursor.getColumnIndex(
                                "pages"));
                        double price =
                                cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "书名是: "+name);
                        Log.d(TAG, "书的作者是："+author);
                        Log.d(TAG, "书的页数是：" + pages);
                        Log.d(TAG, "书的价格为：" + price);


                    }
                    //关闭游标
                    cursor.close();
                }
            }
        });

        //更新数据
        Button btn_updateData = (Button) findViewById(R.id.update_data);
        btn_updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取uri
                Uri uri = Uri.parse("content://com.example.mydatabasehelper" +
                        ".provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name","星辰变");
                values.put("author","辰东");
                values.put("price",25);
                getContentResolver().update(uri,values,null,null);

            }
        });

        //删除数据

        Button btn_deleteData = (Button) findViewById(R.id.delete_data);
        btn_deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.mydatabasehelper" +
                        ".provider/book/" + newId);
                getContentResolver().delete(uri,null,null);
            }
        });

    }



}