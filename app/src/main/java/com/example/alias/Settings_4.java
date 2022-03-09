package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Settings_4 extends AppCompatActivity {

    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_4);

        Button Next = findViewById(R.id.button_settings_next_4);
        Next.setOnClickListener(v -> startActivity(new Intent(Settings_4.this, Team_Scores_5.class)));
    }
}