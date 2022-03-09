package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Team_Scores_5 extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_5);
        Button Start = findViewById(R.id.button_start_game_5);
       // Start.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, PlayingPhase_6.class)));

        Button Previous = findViewById(R.id.button_previous_5);
        Previous.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, Settings_4.class)));


        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(view.getContext(),PlayingPhase_6.class);
                Bundle extras = getIntent().getExtras();
                x.putExtra("timer",extras.getInt("timer"));
                startActivity(x);
            }
        });
    }


}
