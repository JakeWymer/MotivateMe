package com.jakey.motivateme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import com.jakey.motivateme.R;

public class SetWorkout extends ActionBarActivity {

    public static int workoutHour = 18;
    public static int workoutMinute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_workout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_workout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toSettings(View view){

        TimePicker timePick = (TimePicker) findViewById(R.id.workoutTimePicker);
        workoutHour = timePick.getCurrentHour();
        workoutMinute = timePick.getCurrentMinute();


        Intent i = new Intent(this, Dashboard.class);
        startActivity(i);
    }
}
