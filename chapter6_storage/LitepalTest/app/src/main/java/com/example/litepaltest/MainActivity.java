package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加数据库
        Button btn_create = (Button) findViewById(R.id.btn_createDB);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
            }
        });

        //添加数据
        Button btn_addData = (Button) findViewById(R.id.btn_addData);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("斗破苍穹");
                book.setAuthor("天蚕土豆");
                book.setPages(1000);
                book.setPrice(20);
                book.save();
            }
        });

        //更新数据
        Button btn_update = (Button) findViewById(R.id.btn_UpdateData);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(15);
                book.updateAll("name=?", "斗破苍穹");
            }
        });

        //删除数据
        Button btn_deleteData = (Button) findViewById(R.id.btn_DeleteData);
        btn_deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Book.class);
            }
        });
        //查询数据
        Button btn_query = (Button) findViewById(R.id.btn_query);
        btn_deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取book
                List<Book> books = LitePal.findAll(Book.class);
                //显示在界面上
                TextView showView = findViewById(R.id.txt_show);
                for(Book book:books) {
                    Toast.makeText(getApplicationContext(),"名字是" +book.getName(),
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"作者是" +book.getAuthor(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
