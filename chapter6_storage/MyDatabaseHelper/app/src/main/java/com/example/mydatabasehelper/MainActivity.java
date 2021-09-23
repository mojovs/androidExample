package com.example.mydatabasehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;      //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"bookStore.db",null,3);
        Button createDatabase = (Button) findViewById(R.id.btn_createDB);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });

        //按按钮，向数据库里添加数据
        Button addData = findViewById(R.id.btn_addData);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始添加数据
                values.put("name","GOGO");
                values.put("author","G");
                values.put("pages",255);
                values.put("price",20);
                db.insert("Book",null,values);  //表里写入数据
                values.clear();
            }
        });

        //更新数据按钮
        Button updateData = findViewById(R.id.btn_UpdateData);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //放入数据
                values.put("price",10);
                db.update("Book",values,"name = ?",new String[]{"GOGO"});

            }
        });

        //按钮查询数据
        Button btn_query = findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst())
                {
                    //遍历所有对象
                    do {
                        String name = cursor.getString(cursor.getColumnIndex(
                                "name"));
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}