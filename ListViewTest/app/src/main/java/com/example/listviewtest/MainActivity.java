package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private String[] data={"Apple","Banana","Orange","Watermelon",
            "Apple","Banana","Pineapple","Strawberry","Cherry","Mango",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化水果列表
        initFruit();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,
                R.layout.fruit_item,fruitList);
        ListView listView= (ListView)  findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
    //初始化数据
    private void initFruit()
    {
        for(int i =0;i<20;i++)
        {
            //创建对象
            //Fruit apple = new Fruit("Apple",R.drawable.apple_pic);
            Fruit apple = new Fruit("Apple",R.drawable.apple_pic);
            //加入列表
            fruitList.add(apple);


        }
    }
}