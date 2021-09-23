package com.example.broadcastsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastRecver extends BroadcastReceiver {

    private static final String TAG = "SENDER";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"接受到消息",Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onReceive: 接受到消息");
    }
}