package com.example.uitest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEdit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取按键
        Button btn1= (Button)findViewById(R.id.btn_1);
        txtEdit1= (EditText)findViewById(R.id.txtEdit_1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn_1:
                String inputText = txtEdit1.getText().toString();
                Log.d("MainActivity", inputText);
                Toast.makeText(MainActivity.this,inputText,
                        Toast.LENGTH_SHORT).show();
                //开始创建对话框
                ProgressDialog progressDialog =
                        new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is Dialog");
                progressDialog.setMessage("Loading");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}