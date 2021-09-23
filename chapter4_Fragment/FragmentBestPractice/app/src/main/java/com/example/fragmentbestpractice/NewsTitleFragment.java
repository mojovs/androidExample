package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        //从界面中找到recyclerview
        RecyclerView newsTitleRecyclerView =
                (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity());
        //给view设置布局管理器
        newsTitleRecyclerView.setLayoutManager(linearLayoutManager);
        //获取适配器，并且设置适配器
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(newsAdapter);
        return view;
    }
    //创建一个新闻列表
    private List<News> getNews()
    {
        List<News> newsList = new ArrayList<News>();
        for(int i =1;i<=50;i++)
        {
            News news = new News();
            //设置内容和标题
            news.setTitle("This is news title");
            news.setContent("This is news Content"+getRandomLengthContent(
                    "Good"));
            newsList.add(news);
        }
        return newsList;
    }
    //获取一随机的文本内容
    private String getRandomLengthContent(String content)
    {
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i =0 ;i<length;i++)
        {
            builder.append(content);
        }
        return  builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //查找活动
        if(getActivity().findViewById(R.id.news_contet_layout) != null)
        {
            isTwoPane =true;    //双页模式
        }
        else
        {
            isTwoPane = false;
        }
    }
    //新闻标题适配器
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewsList;
        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView newsTitleText;
            //构造
            public ViewHolder(View view)
            {
                super(view);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
            }
        }
        //构造
        public NewsAdapter(List<News> newsList)
        {
            mNewsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    //获取item
                    News news =
                            mNewsList.get(holder.getAbsoluteAdapterPosition());
                    if(isTwoPane)
                    {
                        //如果是双页模式，那么刷新内容
                        //找到内容碎片
                        NewsContentFragment newsContentFragment =
                                (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        //刷新内容界面
                        newsContentFragment.refresh(news.getTitle(),
                                news.getContent());;

                    }else
                    {
                        //如果单页模式，直接启动新活动
                        NewsContentActivity.actionStart(getActivity(),
                                news.getTitle(),news.getContent());

                    }

                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            //设置holder内容
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}
