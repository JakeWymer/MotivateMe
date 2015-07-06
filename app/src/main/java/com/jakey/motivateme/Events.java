package com.jakey.motivateme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jakey.motivateme.models.EventRecord;

import java.util.ArrayList;


public class Events extends NavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_events);

        ArrayList<EventRecord> eventRecords = (ArrayList<EventRecord>) EventRecord.listAll(EventRecord.class);
        ListView goalsList = (ListView) findViewById(R.id.listView_goals);
        goalsList.setAdapter(new EventsAdapter(this, eventRecords));
    }

    public void startAddGoal(View v) {
        Intent addGoal = new Intent(this, AddEvent.class);
        startActivity(addGoal);
    }

    public class EventsAdapter extends ArrayAdapter<EventRecord> {
        public EventsAdapter(Context context, ArrayList<EventRecord> events) {
            super(context, 0, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            EventRecord event = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
            }

            TextView eventNameTV = (TextView) convertView.findViewById(R.id.event_name_text_view);
            TextView eventDateTV = (TextView) convertView.findViewById(R.id.event_date_text_view);

            eventNameTV.setText(event.getName());
            eventDateTV.setText(event.getDate());

            return convertView;
        }
    }

}
