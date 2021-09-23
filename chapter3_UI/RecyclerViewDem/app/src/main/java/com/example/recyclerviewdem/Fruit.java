package com.example.recyclerviewdem;

public class Fruit {
    private String name;    //水果名称
    private int imageID;    //水果图片id
    public Fruit(String name,int imageID)
    {
        this.name = name;
        this.imageID = imageID;
    }
    public String getName()
    {
        return  name;
    }
    public int getImageID()
    {
        return  imageID;
    }


}
