package com.jakey.motivateme;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jakey.motivateme.models.LogRecord;

import java.util.ArrayList;


public class Stats extends NavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_stats);
        ArrayList<LogRecord> logRecords = (ArrayList<LogRecord>) LogRecord.listAll(LogRecord.class);
        ListView myList = (ListView) findViewById(com.jakey.motivateme.R.id.listView_log);
        myList.setAdapter(new StatsAdapter(this, logRecords));
    }

    public class StatsAdapter extends ArrayAdapter<LogRecord> {
        public StatsAdapter(Context context, ArrayList<LogRecord> events) {
            super(context, 0, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            LogRecord log = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_log, parent, false);
            }

            TextView logDateTV = (TextView) convertView.findViewById(R.id.log_date_text_view);
            TextView weightTV = (TextView) convertView.findViewById(R.id.weight_text_view);
            TextView dietTV = (TextView) convertView.findViewById(R.id.diet_text_view);
            TextView workoutTV = (TextView) convertView.findViewById(R.id.workout_text_view);

            logDateTV.setText(log.getDate());
            String weight = log.getWeight() == null ? "" : log.getWeight() + " lbs.";
            weightTV.setText(weight);
            dietTV.setText(log.getDiet());
            workoutTV.setText(log.getWorkout() ? "Yes" : "No");

            return convertView;
        }
    }
}
