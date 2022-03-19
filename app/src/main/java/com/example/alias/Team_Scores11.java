package com.example.alias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Team_Scores11 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_11);

        // button -> come back to the main menu
        Button play_again = findViewById(R.id.button_play_again);
        play_again.setOnClickListener(v -> startActivity(new Intent(Team_Scores11.this, MainMenu_1.class)));

    }



}
