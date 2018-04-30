package com.example.renato.trabalho;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Renato on 30/03/2018.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       // Intent myIntent = new Intent("com.tutsplus.my.first.AIRPLANE_MODE");
       // context.sendBroadcast(myIntent);
        // Check if you have the right broadcast intent
        if (intent.getAction().equals("android.intent.action.AIRPLANE_MODE")) {
            // Get all extras
            Bundle extras = intent.getExtras();

            // Fetch the boolean extra using getBoolean()
            boolean state = extras.getBoolean("state");

            // Log the value of the extra
            Log.d("MYAPP", "AIRPLANE MODE: " + state);
        }
    }
}
