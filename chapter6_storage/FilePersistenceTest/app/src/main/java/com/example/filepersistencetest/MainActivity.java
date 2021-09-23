package com.example.filepersistencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //从界面获取组件
        edit = (EditText) findViewById(R.id.txtEdit);
        //获取文件内容
        String input = load();
        if(!TextUtils.isEmpty(input))
        {
            edit.setText(input);
            edit.setSelection(input.length());
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);
    }
    public void save(String inputTxt)
    {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputTxt);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if(writer != null)
                    writer.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public String load()
    {
        FileInputStream in = null;
        BufferedReader reader = null;
        //开始构造字符串
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null)
            {
                content.append(line);
            }

        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if(reader != null)
            {
                try {
                    reader.close();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

}