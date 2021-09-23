package com.example.fragmentbestpractice;

public class News {
    private String title;
    private String content;

    public String getTitle()
    {
        return  title;
    }
    public String getContent()
    {
        return content;
    }
    //设置文本内容
    public void setContent(String content)
    {
        this.content = content;
    }
    //设置标题内容
    public void setTitle(String title)
    {
        this.title=title;
    }

}
