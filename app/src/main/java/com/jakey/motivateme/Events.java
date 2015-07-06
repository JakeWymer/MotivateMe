package com.jakey.motivateme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jakey.motivateme.models.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Events extends NavActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_events);

        final ArrayList<Event> events = (ArrayList<Event>) Event.listAll(Event.class);
        ListView EventsList = (ListView) findViewById(R.id.listView_events);
        EventsList.setAdapter(new EventsAdapter(this, events));

        EventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editEvent = new Intent(view.getContext(), AddEvent.class);
                Event event = events.get(position);
                try {
                    JSONObject eventJson = event.toJSON();
                    editEvent.putExtra("event", eventJson.toString());
                    startActivity(editEvent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startAddEvent(View v) {
        Intent addEvent = new Intent(this, AddEvent.class);
        startActivity(addEvent);
    }

    public class EventsAdapter extends ArrayAdapter<Event> {
        public EventsAdapter(Context context, ArrayList<Event> events) {
            super(context, 0, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Event event = getItem(position);

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
