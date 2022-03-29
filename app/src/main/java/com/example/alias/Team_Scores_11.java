package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Team_Scores_11 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_11);


        TextView label_winner_team = findViewById(R.id.label_winner_announcement_11);
        label_winner_team.setText(Main.game.getWinnerTeam().getName());


    }
}
