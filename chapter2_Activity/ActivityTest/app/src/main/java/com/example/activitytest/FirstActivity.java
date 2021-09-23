package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button btn1 = (Button)findViewById(R.id.btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(getApplicationContext(), "You Click Btn1", Toast.LENGTH_SHORT).show();
                                        //Intent intent = new Intent("com.example.activitytest.ACTION_START");

                                        //Intent intent = new Intent(Intent.ACTION_VIEW);
                                        //intent.setData(Uri.parse("http://www.baidu.com"));

                                        Intent intent = new Intent(FirstActivity.this,thirdActivity.class);
                                        String data = "传递的数据";
                                        intent.putExtra("extra_data", data);

                                        //startActivity(intent);
                                        startActivityForResult(intent,1);
                                    }

                                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_item:
                Toast.makeText(this, "You Clicked add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You Clickded remove", Toast.LENGTH_SHORT).show();
            default:
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)    //判断请求码
        {
            case 1:
            if(resultCode == RESULT_OK)
            {
                String returnData = data.getStringExtra("data_return");
                Log.d("FirstActivity ",returnData);
            }
            break;

            default:
        }

    }
}