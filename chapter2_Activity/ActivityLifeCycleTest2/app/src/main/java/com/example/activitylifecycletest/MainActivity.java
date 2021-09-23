package com.example.activitylifecycletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        if(null != savedInstanceState)
        {
            String tempData = savedInstanceState.getString("temp_data");
            Log.d(TAG, tempData);
        }
        Button startNormal = (Button)findViewById(R.id.start_normal);
        Button startDialog= (Button)findViewById(R.id.start_dialog);

        //按键监听
        startNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动普通活动
                Intent intent = new Intent(MainActivity.this,
                        NornalActivity.class);
                startActivity(intent);
            }
        });
     //按键监听
        startDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动普通活动
                Intent intent = new Intent(MainActivity.this,
                        DialogActivity.class);
                startActivity(intent);
            }
        });

        }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        String data = "保存的状态";
        outState.putString("temp_data",data);       //保存状态
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }



}