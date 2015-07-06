package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jakey.motivateme.models.EventRecord;

public class AddEvent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
    }

    public void saveEvent(View v) {
        EditText eventNameInput = (EditText) findViewById(R.id.event_name_input);
        DatePicker eventDatePicker = (DatePicker) findViewById(R.id.event_date_picker);
        String eventName = eventNameInput.getText() == null ? null : eventNameInput.getText().toString();
        String eventDate = String.format("%04d-%02d-%02d", eventDatePicker.getYear(), eventDatePicker.getMonth(), eventDatePicker.getDayOfMonth());
        EventRecord eventRecord = new EventRecord(eventName, eventDate);
        eventRecord.save();

        Intent goals = new Intent(this, Events.class);
        startActivity(goals);
    }

}
