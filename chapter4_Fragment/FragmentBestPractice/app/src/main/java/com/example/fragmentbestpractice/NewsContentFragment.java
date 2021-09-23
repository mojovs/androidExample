package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_contet_frag,container,false);
        return view;
    }
    public void refresh(String newsTitle,String newsContent)
    {
        //获取layout，并让其显示
        View visibilitylayout = view.findViewById(R.id.visibility_layout);
        visibilitylayout.setVisibility(View.VISIBLE);
        TextView newsTitleText = view.findViewById(R.id.news_title);
        TextView newsContentText = view.findViewById(R.id.news_content);
        //给标题和内容组件赋值
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);
    }
}
