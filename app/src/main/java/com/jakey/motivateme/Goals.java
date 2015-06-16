package com.jakey.motivateme;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Goals extends Activity {


    DBAdapter database = new DBAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_goals);

        int thisUser= Signin.getCurrentUser();

        popLV();
    }


//change to add goals xml layout
    public void changeToAddGoal(View view)
    {
        setContentView(com.jakey.motivateme.R.layout.addgoals);
    }

//gets text, opens db, adds info in
    public void addEvent(View v) throws SQLException

    {
        Log.d("test", "adding");

        int thisUser = Signin.getCurrentUser();

        //get data from form
        EditText titleTxt = (EditText)findViewById(com.jakey.motivateme.R.id.txttitle);
        EditText dayTxt = (EditText)findViewById(com.jakey.motivateme.R.id.txtDay);
        EditText monthTxt = (EditText)findViewById(com.jakey.motivateme.R.id.txtMonth);
        EditText yearTxt = (EditText)findViewById(com.jakey.motivateme.R.id.txtYear);
        EditText goalTxt = (EditText)findViewById(com.jakey.motivateme.R.id.txtgoal);

        int goalYear = Integer.parseInt(yearTxt.getText().toString());
        int goalMonth = Integer.parseInt(monthTxt.getText().toString());
        int goalDay = Integer.parseInt(dayTxt.getText().toString());
        String monthString = monthTxt.getText().toString();
        String dayString = dayTxt.getText().toString();
        String yearString = yearTxt.getText().toString();

        String dateFormat = monthString + "/" + dayString + "/" +
                yearString;

        GregorianCalendar goalDate = new GregorianCalendar(goalYear, goalMonth, goalDay);
        long goalTime = goalDate.getTimeInMillis();

        //insert event into db
        database.open();
             long id= database.insertEvent(titleTxt.getText().toString(),
                dateFormat, goalTxt.getText().toString(),thisUser);
        database.close();

        titleTxt.setText("");
        dayTxt.setText("");
        goalTxt.setText("");
        monthTxt.setText("");
        yearTxt.setText("");


        Calendar calendar = Calendar.getInstance();
        Intent myIntent = new Intent(Goals.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Goals.this, 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis() + 5000, pendingIntent);





        Intent toMenu = new Intent(this, Main.class);
        startActivity(toMenu);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.jakey.motivateme.R.menu.goals, menu);
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

    public void popLV()
    {
        int thisUser = Signin.getCurrentUser();



        database.open();
        Cursor c= database.getEvents(thisUser);

        database.close();

        startManagingCursor(c);


        String[] fromFieldNames = new String[]
                {DBAdapter.KEY_TITLE, DBAdapter.KEY_DATE, DBAdapter.KEY_GOAL};
        int[] toViewIDs = new int[]
                {com.jakey.motivateme.R.id.list_title, com.jakey.motivateme.R.id.list_date, com.jakey.motivateme.R.id.list_goal};

        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        com.jakey.motivateme.R.layout.listview_layout,
                        c,
                        fromFieldNames,
                        toViewIDs,
                        0
                );

        ListView myList = (ListView) findViewById(com.jakey.motivateme.R.id.listView_goals);
        myList.setAdapter(myCursorAdapter);
    }

}
