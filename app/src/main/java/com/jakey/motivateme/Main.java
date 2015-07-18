package com.jakey.motivateme;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class Main extends NavActivity {

    DailyLog dailyLog = DailyLog.findByDate(new Date());

    EditText weightEditText;
    ToggleButton workoutToggle;
    RadioGroup dietRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_main);

        dietAlarm();
        weightAlarm();
        workoutAlarm();

        weightEditText = (EditText) findViewById(R.id.weightinput);
        dietRadioGroup = (RadioGroup) findViewById(R.id.radioGroupDiet);
        workoutToggle = (ToggleButton) findViewById(R.id.workoutToggle);

        // update values if today's record already exists
        if (dailyLog != null) {
            weightEditText.setText(String.valueOf(dailyLog.getWeight()));
            workoutToggle.setChecked(dailyLog.getWorkout());
            if (dailyLog.getDiet() != null) {
                if (dailyLog.getDiet().equals(getString(R.string.diet_healthy))) {
                    dietRadioGroup.check(R.id.diet_healthy_btn);
                } else if (dailyLog.getDiet().equals(getString(R.string.diet_average))) {
                    dietRadioGroup.check(R.id.diet_average_btn);
                } else if (dailyLog.getDiet().equals(getString(R.string.diet_poor))) {
                    dietRadioGroup.check(R.id.diet_poor_btn);
                }
            }
        }

        // add listeners for changes to today's log
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                saveStats();
            }
        });

        workoutToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                saveStats();
            }
        });

        dietRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                saveStats();
            }
        });

    }

    public void saveStats() {
        RadioButton radioButtonDiet = (RadioButton) findViewById(dietRadioGroup.getCheckedRadioButtonId());
        String dietTxt = radioButtonDiet == null ? null : (String) radioButtonDiet.getText();
        String weightString = weightEditText.getText().toString();
        Integer weight = weightString.equals("") ? null : Integer.parseInt(weightString);
        dailyLog = new DailyLog(weight, dietTxt, workoutToggle.isChecked());
        dailyLog.saveOrUpdate();
    }

    public void toSettings(View view){

        Intent i = new Intent(this, UserSettings.class);
        startActivity(i);
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
            calendar.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
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
            calendar.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
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
            calendar.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+1);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, workoutPendingIntent);
        }

    }

}