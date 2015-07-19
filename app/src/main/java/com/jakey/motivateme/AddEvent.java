package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jakey.motivateme.models.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class AddEvent extends Activity {

    Event event;
    EditText eventNameInput;
    DatePicker eventDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        eventNameInput = (EditText) findViewById(R.id.event_name_input);
        eventDatePicker = (DatePicker) findViewById(R.id.event_date_picker);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String eventString = extras.getString("event");
            try {
                event = new Event(new JSONObject(eventString));
                eventNameInput.setText(event.getName());
                Date date = Event.DATE_FORMAT.parse(event.getDate());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                eventDatePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException | JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void saveEvent(View v) {
        String eventName = eventNameInput.getText() == null ? null : eventNameInput.getText().toString();
        String eventDate = String.format("%04d-%02d-%02d", eventDatePicker.getYear(), eventDatePicker.getMonth() + 1, eventDatePicker.getDayOfMonth());

        if (event == null) {
            event = new Event();
        }
        event.setName(eventName);
        event.setDate(eventDate);
        event.save();

        Intent goals = new Intent(this, Events.class);
        startActivity(goals);
    }
}