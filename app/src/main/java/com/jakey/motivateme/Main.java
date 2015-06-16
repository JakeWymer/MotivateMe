package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Main extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_main);

    }

    public void TodayLog(View view)
    {
        Intent toTodaysStats = new Intent(this, LogToday.class);
        startActivity(toTodaysStats);
    }

    public void MyGoals(View view){
        Intent toGoals = new Intent(this, Goals.class);
        startActivity(toGoals);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.jakey.motivateme.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == com.jakey.motivateme.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
