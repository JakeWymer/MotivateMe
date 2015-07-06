package com.jakey.motivateme;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.jakey.motivateme.models.DailyLog;

import java.util.Date;

public class Main extends NavActivity {

    DailyLog dailyLog = DailyLog.findByDate(new Date());

    EditText weightEditText;
    ToggleButton workoutToggle;
    RadioGroup dietRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jakey.motivateme.R.layout.activity_main);

        weightEditText = (EditText) findViewById(R.id.weightinput);
        dietRadioGroup = (RadioGroup) findViewById(R.id.radioGroupDiet);
        workoutToggle = (ToggleButton) findViewById(R.id.workoutToggle);

        // update values if today's record already exists
        if (dailyLog != null) {
            weightEditText.setText(String.valueOf(dailyLog.getWeight()));
            workoutToggle.setChecked(dailyLog.getWorkout());
            if (dailyLog.getDiet() != null) {
                if (dailyLog.getDiet().equals(getString(R.string.diet_healthy))) {
                    dietRadioGroup.check(R.id.diet_healthy_btn);
                } else if (dailyLog.getDiet().equals(getString(R.string.diet_average))) {
                    dietRadioGroup.check(R.id.diet_average_btn);
                } else if (dailyLog.getDiet().equals(getString(R.string.diet_poor))) {
                    dietRadioGroup.check(R.id.diet_poor_btn);
                }
            }
        }

        // add listeners for changes to today's log
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                saveStats();
            }
        });

        workoutToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                saveStats();
            }
        });

        dietRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                saveStats();
            }
        });
    }

    public void saveStats() {
        RadioButton radioButtonDiet = (RadioButton) findViewById(dietRadioGroup.getCheckedRadioButtonId());
        String dietTxt = radioButtonDiet == null ? null : (String) radioButtonDiet.getText();
        String weightString = weightEditText.getText().toString();
        Integer weight = weightString.equals("") ? null : Integer.parseInt(weightString);
        dailyLog = new DailyLog(weight, dietTxt, workoutToggle.isChecked());
        dailyLog.saveOrUpdate();
    }
}
