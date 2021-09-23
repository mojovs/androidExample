package com.example.contactstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;   //适配器
    List<String> contactsList = new ArrayList<>();  //界面列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始获取ListView组件
        ListView contactsView = (ListView) findViewById(R.id.contacts_view);
        //适配器
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, contactsList);
        //给ListView设置适配器
        contactsView.setAdapter(adapter);


        //获取权限
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},1);
        }else
        {
            readContacts();
        }

    }

    //从系统里面读取联系人
    private void readContacts()
    {
        Cursor cursor = null;   //创建游标
        try {
            //获取游标
            cursor =
                    getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor != null)
            {
                while(cursor.moveToNext())
                {
                    //获取联系人名称
                    String displayName =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String number =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //往列表里面添加内容
                    contactsList.add(displayName + "\n" +number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if(cursor != null)
            {
                //最后关闭游标
                cursor.close();
            }
        }
    }

    //重载，查询权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
        switch(requestCode)
        {
            case 1:
                if(grantResults.length >0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    //执行动作
                    readContacts();
                }else   //查询失败
                {
                    Toast.makeText(this,"你拒绝了请求权限",Toast.LENGTH_SHORT).show();
                }
            default:

        }
    }
}