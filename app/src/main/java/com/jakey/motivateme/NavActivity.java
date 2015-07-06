package com.jakey.motivateme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

public class NavActivity extends Activity {

    @Override
    protected void onStart() {
        super.onStart();

        Button btnToday = (Button) findViewById(R.id.btnToday);
        Button btnHistory = (Button) findViewById(R.id.btnHistory);
        Button btnGoals = (Button) findViewById(R.id.btnGoals);
        Button btnSelected = null;

        if (this instanceof Main) {
            btnSelected = btnToday;
        } else if (this instanceof Stats) {
            btnSelected = btnHistory;
        } else if (this instanceof Events) {
            btnSelected = btnGoals;
        }

        if (btnSelected != null) {
            btnSelected.setBackgroundColor(Color.WHITE);
        }

        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(view.getContext(), Main.class);
                startActivity(mainActivity);
                overridePendingTransition(0, 0);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyActivity = new Intent(view.getContext(), Stats.class);
                startActivity(historyActivity);
                overridePendingTransition(0, 0);
            }
        });

        btnGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goalsActivity = new Intent(view.getContext(), Events.class);
                startActivity(goalsActivity);
                overridePendingTransition(0, 0);
            }
        });
    }
}