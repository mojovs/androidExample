package com.example.litepaltest;

import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {
    private int id;
    private String author;
    private double price;
    private int pages;
    private String name;

    //获取信息
    public  int getId()
    {
        return  this.id;
    }
    public  String getAuthor()
    {
        return  this.author;
    }
    public double getPrice()
    {
        return this.price;
    }
    public int getPages()
    {
        return  this.pages;
    }
    public String getName()
    {
        return this.name;
    }

    //设置信息
    public void setId(int id)
    {
        this.id = id;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public  void setPrice(double price)
    {
        this.price = price;
    }
    public void setPages(int pages)
    {
        this.pages = pages;
    }
    public void setName(String name)
    {
        this.name = name;
    }

}
