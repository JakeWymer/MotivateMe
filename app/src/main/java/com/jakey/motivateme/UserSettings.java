package com.jakey.motivateme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.jakey.motivateme.R;

public class UserSettings extends ActionBarActivity {

    private int weightHour;
    private int weightMinute;

    private int workoutHour;
    private int workoutMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_settings, menu);
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



   public void toSetDiet(View view){
        Intent setDiet = new Intent(this, setDiet.class);
        startActivity(setDiet);
    }

    public void toSetWeight(View view){
        Intent setWeight = new Intent(this, SetWeight.class);
        startActivity(setWeight);
    }

    public void toSetWorkout(View view){
        Intent setWorkout = new Intent(this, SetWorkout.class);
        startActivity(setWorkout);
    }

    public void toMain(View view){
        Intent i = new Intent(this, Dashboard.class);
        startActivity(i);
    }

}
