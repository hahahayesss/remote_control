package com.r00t.remotecontrol.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.r00t.remotecontrol.R;
import com.r00t.remotecontrol.services.ControlListener;

public class NotificationUtils extends ContextWrapper {
    public static final String BASE_CHANNEL_ID = "com.r00t.remotecontrol.BASE_CHANNEL";
    public static final String BASE_CHANNEL_NAME = "Base Channel";

    private NotificationManager notificationManager;

    public NotificationUtils(Context base) {
        super(base);
        createChannel();
    }

    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(
                BASE_CHANNEL_ID, BASE_CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getNotificationManager().createNotificationChannel(channel);
    }

    public void startNotification() {
        getNotificationManager().notify(0, getBaseNotification());
    }

    public void stopNotification() {
        getNotificationManager().cancel(0);
    }

    private Notification getBaseNotification() {
        RemoteViews largeLayout = new RemoteViews(getPackageName(), R.layout.notification_large);

        Intent intent = new Intent(this, ControlListener.class);
        intent.setAction("KEY_ACTION");

        largeLayout.setOnClickPendingIntent(R.id.leftArrow,
                createClickIntent("ON_LEFT_ARROW"));
        largeLayout.setOnClickPendingIntent(R.id.upArrow,
                createClickIntent("ON_UP_ARROW"));
        largeLayout.setOnClickPendingIntent(R.id.downArrow,
                createClickIntent("ON_DOWN_ARROW"));
        largeLayout.setOnClickPendingIntent(R.id.rightArrow,
                createClickIntent("ON_RIGHT_ARROW"));
        largeLayout.setOnClickPendingIntent(R.id.touchButton,
                createClickIntent("ON_TOUCH"));

        return new NotificationCompat.Builder(getBaseContext(), BASE_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_control)
                .setOngoing(true)
                .setTicker("Remote Control")
                .setSubText("r00t")
                .setNotificationSilent()
                .setCustomBigContentView(largeLayout)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
//                .setContentIntent(pendingIntent)
                .build();
    }

    private PendingIntent createClickIntent(String action) {
        Intent intent = new Intent(this, ControlListener.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(this, 0, intent, 0);
    }

    public NotificationManager getNotificationManager() {
        if (notificationManager == null)
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager;
    }
}
