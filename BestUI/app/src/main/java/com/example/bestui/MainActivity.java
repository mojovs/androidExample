package com.example.bestui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText; //输入
    private Button btnSend;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化消息
        initMsg();
        //绑定ui btn和editText
        inputText = (EditText)findViewById(R.id.input_text);
        btnSend= (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);

        //
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        //设置适配器
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String ctt = inputText.getText().toString();
                //如果没有内容
                if(!"".equals(ctt)) {
                    Msg msg = new Msg(ctt,Msg.TYPE_SENT);
                    msgList.add(msg);

                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");

                }

            }
        });

    }

    private void initMsg()
    {
        Msg msg1 = new Msg("Hello guy.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello ,who is that?.",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("THis is Tom.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}