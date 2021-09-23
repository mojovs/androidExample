package com.example.bestui;

public class Msg {
    //发送和接受标志
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content; //发送的内容
    private int type;   //类型
    public Msg(String ctt,int type)
    {
        this.content = ctt;
        this.type = type;
    }
    public String getContent()
    {
        return this.content;
    }
    public int getType()
    {
        return  this.type;
    }
}
