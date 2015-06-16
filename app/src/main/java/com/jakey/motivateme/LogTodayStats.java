package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jakey.motivateme.DBAdapter;
import com.jakey.motivateme.Main;
import com.jakey.motivateme.Signin;

import java.util.Calendar;


public class LogTodayStats extends Activity {

    DBAdapter database = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_log_today_stats);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.jakey.motivateme.R.menu.today_stats, menu);
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

    public void changeToLog(View view)
    {
        setContentView(com.jakey.motivateme.R.layout.activity_log_today);
    }

    public void addLog(View v)
    {
        int thisUser = Signin.getCurrentUser();

        RadioGroup radioGroupDiet = (RadioGroup) findViewById(com.jakey.motivateme.R.id.radioGroupDiet);
        int selectedDiet = radioGroupDiet.getCheckedRadioButtonId();
        RadioButton radioButtonDiet = (RadioButton) findViewById(selectedDiet);
        String dietTxt = (String) radioButtonDiet.getText();

        RadioGroup radioGroupWork = (RadioGroup) findViewById(com.jakey.motivateme.R.id.radioGroupWO);
        int selectedWO = radioGroupWork.getCheckedRadioButtonId();
        RadioButton radioButtonWO= (RadioButton) findViewById(selectedWO);
        String workoutInfo = (String) radioButtonWO.getText();

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);


        String dateFormat = month + "/" + day + "/" +
                year;

        EditText weightTxt = (EditText)findViewById(com.jakey.motivateme.R.id.weightinput);


        //insert log into db
        database.open();
        long id= database.insertLog(dateFormat, weightTxt.getText().toString(),
                dietTxt, workoutInfo, thisUser);
        database.close();

        Intent toMenu = new Intent(this, Main.class);
        startActivity(toMenu);
    }
}
