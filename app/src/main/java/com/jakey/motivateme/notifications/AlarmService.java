package com.jakey.motivateme.notifications;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.jakey.motivateme.Main;
import com.jakey.motivateme.UserSettings;

public class AlarmService extends Service {


    private NotificationManager mManager;

    public void onStart(Intent intent, int startId)
    {

        Bundle extras = intent.getExtras();

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(),Main.class);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentText(intent.getStringExtra("MyMessage"))
                .setSmallIcon(com.jakey.motivateme.R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("MotivateMe")
                .setVibrate(new long [] {1000,1000,1000})
                .build();

        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this.getApplicationContext(),
                                                                            0,
                                                                            intent1,
                                                                            PendingIntent.FLAG_UPDATE_CURRENT);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(getApplicationContext(), "MotivateMe", intent.getStringExtra("MyMessage"), pendingNotificationIntent);

        mManager.notify(extras.getInt("UID"), notification);

    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}