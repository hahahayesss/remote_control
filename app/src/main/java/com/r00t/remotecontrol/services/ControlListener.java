package com.r00t.remotecontrol.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ControlListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println(intent.getAction());
    }
}
