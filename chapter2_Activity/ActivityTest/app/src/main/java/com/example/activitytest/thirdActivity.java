package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class thirdActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        //获取按钮
        Button btn3 = (Button) findViewById(R.id.btn_3);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return","Hello, the first one");
                setResult(RESULT_OK,intent);
                ActivityCollector.finishAll();   //结束活动三
            }
        });
        //获取传送进活动的消息
    }
}