package com.jakey.motivateme;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.jakey.motivateme.models.DailyLog;
import com.jakey.motivateme.notifications.AlarmReceiver;
import com.jakey.motivateme.notifications.AlarmService;

import java.util.Calendar;
import java.util.Date;

public class Dashboard extends NavActivity {

    DailyLog dailyLog = DailyLog.findByDate(new Date());

    ToggleButton workoutToggle;
    RadioGroup dietRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        alarmHelper();
    }

    public void toSettings(View view){

        Intent i = new Intent(this, UserSettings.class);
        startActivity(i);
    }


    public void alarmHelper(){
        dietAlarm();
        weightAlarm();
        workoutAlarm();
    }

    public void dietAlarm(){

        PendingIntent dietPendingIntent;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar currentTime = Calendar.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, setDiet.dietHour);
        calendar.set(Calendar.MINUTE, setDiet.dietMinute);

        Intent dietIntent = new Intent(this, AlarmReceiver.class);
        Bundle extras = new Bundle();
        extras.putString("MyMessage", "Don't forget to log your diet!");
        extras.putInt("UID", 0);
        dietIntent.putExtras(extras);
        dietPendingIntent = PendingIntent.getBroadcast(this, 0, dietIntent,0);
        alarmManager.cancel(dietPendingIntent);



        if(currentTime.getTimeInMillis()<calendar.getTimeInMillis()){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, dietPendingIntent);
        }
        else{
            calendar.add(Calendar.DATE, 1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, dietPendingIntent);
        }

    }


    public void weightAlarm(){

        PendingIntent weightPendingIntent;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar currentTime = Calendar.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, SetWeight.weightHour);
        calendar.set(Calendar.MINUTE, SetWeight.weightMinute);

        Intent weightIntent = new Intent(this, AlarmReceiver.class);
        Bundle extras = new Bundle();
        extras.putString("MyMessage", "Don't forget to log your weight!");
        extras.putInt("UID", 1);
        weightIntent.setAction("actionstring" + System.currentTimeMillis());
        weightIntent.putExtras(extras);
        weightPendingIntent = PendingIntent.getBroadcast(this, 1, weightIntent, 0);

        alarmManager.cancel(weightPendingIntent);


        if(currentTime.getTimeInMillis()<calendar.getTimeInMillis()){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, weightPendingIntent);
        }
        else{
            calendar.add(Calendar.DATE, 1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, weightPendingIntent);
        }

    }

    public void workoutAlarm(){

        PendingIntent workoutPendingIntent;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar currentTime = Calendar.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, SetWorkout.workoutHour);
        calendar.set(Calendar.MINUTE, SetWorkout.workoutMinute);
        calendar.set(Calendar.MILLISECOND, 0);
        Intent workoutIntent = new Intent(this, AlarmReceiver.class);


        Bundle extras = new Bundle();
        extras.putString("MyMessage", "Did you workout today?");
        extras.putInt("UID", 2);
        workoutIntent.setAction("actionstring" + System.currentTimeMillis());
        workoutIntent.putExtras(extras);
        workoutPendingIntent = PendingIntent.getBroadcast(this, 2, workoutIntent, 0);

        alarmManager.cancel(workoutPendingIntent);

        if(currentTime.getTimeInMillis()<calendar.getTimeInMillis()){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, workoutPendingIntent);
        }
        else{
            calendar.add(Calendar.DATE, 1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, workoutPendingIntent);
        }

    }

}