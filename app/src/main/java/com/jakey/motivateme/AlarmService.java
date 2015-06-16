package com.jakey.motivateme;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jakey.motivateme.Signin;


/**
 * Created by jakey on 12/19/14.
 */


public class AlarmService extends Service {


    private NotificationManager mManager;

    public void onStart(Intent intent, int startId)
    {

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),Signin.class);

        Notification notification = new Notification(com.jakey.motivateme.R.drawable.ic_launcher,"Two weeks till goal!", System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.setLatestEventInfo(this.getApplicationContext(), "MotivateMe", "How have you been doing?", pendingNotificationIntent);

        mManager.notify(0, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}