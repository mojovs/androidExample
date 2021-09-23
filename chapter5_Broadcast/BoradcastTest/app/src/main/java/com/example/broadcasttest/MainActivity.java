package com.example.broadcasttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.broadcasttest.R;

public class MainActivity extends AppCompatActivity {

    //广播接受
    private  ConnectivityManager.NetworkCallback networkCallback;
    private ConnectivityManager connectivityManager;
    private static final String TAG = "TEST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开始注册
        networkCallback = new NetworkCallbackImpl();
        //开始请求网络
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        NetworkRequest request = builder.build();
        //获取连接管理器
        connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        connectivityManager.registerNetworkCallback(request,networkCallback);
        //开始获取按钮
        Button btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                intent.setComponent(new ComponentName(getPackageName(),"com" +
                        ".example.broadcasttest.MY_BROADCAST"));
                //发送信号
                Log.d(TAG, "onClick: 发送广播");
                sendBroadcast(intent);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    //回调类
    class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback
    {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            //Toast显示
            Toast.makeText(getBaseContext(),
                    "onAvailable", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLosing(@NonNull Network network, int maxMsToLive) {
            super.onLosing(network, maxMsToLive);
            Toast.makeText(getBaseContext(),
                    "onLosing", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Toast.makeText(getBaseContext(),
                    "onLost", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            Toast.makeText(getBaseContext(),
                    "onCapabilitiesChanged", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties);
            Toast.makeText(getBaseContext(),
                    " onLinkPropertiesChanged", Toast.LENGTH_SHORT).show();
        }
    }


}