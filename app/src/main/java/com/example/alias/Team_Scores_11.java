package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class Team_Scores_11 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_11);

        Button play_again_11 =findViewById(R.id.button_play_again);
        play_again_11.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MainMenu_1.class)));

    }
}
