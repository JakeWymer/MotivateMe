package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class LogToday extends Activity {

    DBAdapter database = new DBAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_log_today);

        popLV();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.jakey.motivateme.R.menu.log_today, menu);
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

    public void changeToAddLog(View view)
    {
        Intent toTodaysStats = new Intent(this, LogTodayStats.class);
        startActivity(toTodaysStats);
    }

    public void popLV()
    {
        int thisUser = Signin.getCurrentUser();

        database.open();
        Cursor c= database.getLogs(thisUser);
        database.close();

        startManagingCursor(c);


        String[] fromFieldNames = new String[]
                {DBAdapter.KEY_DATE, DBAdapter.KEY_WEIGHT, DBAdapter.KEY_DIET, DBAdapter.KEY_WORKOUT};
        int[] toViewIDs = new int[]
                {com.jakey.motivateme.R.id.list_log, com.jakey.motivateme.R.id.list_size, com.jakey.motivateme.R.id.list_diet, com.jakey.motivateme.R.id.list_wo};
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        com.jakey.motivateme.R.layout.listview2_layout,
                        c,
                        fromFieldNames,
                        toViewIDs,
                        0
                );

        ListView myList = (ListView) findViewById(com.jakey.motivateme.R.id.listView_log);

        myList.setAdapter(myCursorAdapter);
    }
}
