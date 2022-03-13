package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoppedPhase_7 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopped_phase_7);

        Button continue_button =findViewById(R.id.button_continue);
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
      // continue_button.setOnClickListener(v -> startActivity(new Intent(StoppedPhase_7.this, PlayingPhase_6.class)));


    }
}
