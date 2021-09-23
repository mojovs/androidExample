package com.example.recyclerviewdem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.text.style.LeadingMarginSpan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        RecyclerView recyclerView =
                (RecyclerView) findViewById(R.id.recyler_view);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);


    }
    private void initFruits()
    {
        for(int i=0;i<20;i++)
        {

            Fruit apple = new Fruit(getRandomLengthName("Apple"),
                    R.drawable.apple_pic);
            fruitList.add(apple);
        }
    }
    //获取随机的水果名
    private  String getRandomLengthName(String name)
    {
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<length;i++)
        {
            builder.append(name);
        }
        return builder.toString();
    }
}