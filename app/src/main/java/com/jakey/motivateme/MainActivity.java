package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jakey.motivateme.models.DailyLog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;
        if (DailyLog.today() == null || DailyLog.today().getWeight() < 100) {
            intent = new Intent(this, RecordWeight.class);
        } else {
            intent = new Intent(this, Dashboard.class);
        }
        startActivity(intent);
    }
}
