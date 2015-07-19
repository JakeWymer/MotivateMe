package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jakey.motivateme.models.DailyLog;

import java.util.Date;

public class RecordWeight extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_weight);
    }

    public void recordWeight(View v) {
        EditText weightText = (EditText) findViewById(R.id.weight_text_input);
        if (weightText.getText() != null) {
            DailyLog today = DailyLog.findByDate(new Date());
            if (today == null) {
                today = new DailyLog();
            }
            today.setWeight(Integer.parseInt(weightText.getText().toString()));
            today.save();

            Intent dashboard = new Intent(v.getContext(), Dashboard.class);
            startActivity(dashboard);
        }
    }

    public void skipWeight(View v) {
        Intent dashboard = new Intent(v.getContext(), Dashboard.class);
        startActivity(dashboard);
    }
}
