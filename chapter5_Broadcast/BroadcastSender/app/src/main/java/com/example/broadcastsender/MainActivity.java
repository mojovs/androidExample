package com.example.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始获取btn
        Button btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.exmaple.broadcasttest" +
                        ".MY_BROADCAST");
                intent.setComponent(new ComponentName("com.example" +
                        ".broadcastsender",
                        "com.example.broadcastsender.BroadcastRecver"));
                sendBroadcast(intent);
            }
        });
    }
}