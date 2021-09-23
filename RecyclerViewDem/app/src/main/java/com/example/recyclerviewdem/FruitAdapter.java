package com.example.recyclerviewdem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    //holder类,作为adapter的传入对象
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view)
        {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_img);
            fruitName= (TextView) view.findViewById(R.id.fruit_name);
        }
    }
    //构造
    public FruitAdapter(List<Fruit> fruitList)
    {
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        //开始创建holder
        ViewHolder holder = new ViewHolder(view);
        //设置按键事件
        holder.fruitView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int position = holder.getAbsoluteAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),
                        "you clicked view"+fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int position = holder.getAbsoluteAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),
                        "you clicked Image "+fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //获取列表里面的item
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitImage.setImageResource(fruit.getImageID());
    }

    //获取item数
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}

