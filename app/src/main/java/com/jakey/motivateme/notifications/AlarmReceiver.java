package com.jakey.motivateme.notifications;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {


        Intent service1 = new Intent(context, AlarmService.class);
        service1.putExtras(intent.getExtras());
        context.startService(service1);

    }

}

