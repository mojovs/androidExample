package com.example.listviewtest;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resID;
    //构造
    public FruitAdapter(Context ctx, int textViewResID, List<Fruit> objects)
    {
        super(ctx,textViewResID,objects);
        resID = textViewResID;
    }
    //获取对象


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit =  getItem(position);       //获取当前项
        View view;
        ViewHolder viewHolder;  //视图句柄
        //获取当前布局
        //如果缓存里面没有view
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resID, parent,
                    false);
            viewHolder = new ViewHolder();
            //获取当前图像控件
            viewHolder.fruitImage= (ImageView)view.findViewById(R.id.fruit_img);
            //获取文字控件
            viewHolder.fruitName =(TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);        //把viewHolder存储进去
        }else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }


        //开始设置
        viewHolder.fruitImage.setImageResource(resID);
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }
    //视图类
    class ViewHolder
    {
        ImageView fruitImage;   //水果图像
        TextView fruitName; //烧锅名称
    }
}
