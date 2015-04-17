package com.example.xiaole.playmysong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            //Toast.makeText(context, "received the Intent's message: " + name, Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, MyService.class);
            i.putExtra("message", name);
           context.startService(i);
        }
}
