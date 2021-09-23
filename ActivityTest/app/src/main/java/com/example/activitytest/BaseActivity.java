package com.example.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取实例的类名
        Log.d(TAG, getClass().getSimpleName());
        //把当前活动添加到管理器里
        ActivityCollector.addAcitivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //从活动管理器里删除当前活动
        ActivityCollector.removeActivity(this);
    }
}
