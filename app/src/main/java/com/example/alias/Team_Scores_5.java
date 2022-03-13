package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Team_Scores_5 extends AppCompatActivity{

    ArrayList<Team> teams;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_5);
        //setContentView(R.layout.item_team);

        Button Start = findViewById(R.id.button_start_game_5);
       // Start.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, PlayingPhase_6.class)));

        Button Previous = findViewById(R.id.button_previous_5);
        Previous.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, Settings_4.class)));
        Previous.setOnClickListener(v -> finish());

        //initiating teams
        RecyclerView rvTeams = (RecyclerView) findViewById(R.id.rv_teams);
        teams=Team.createTeamList(Team_Count_3.num_of_teams);
       TeamAdapter adapter =new TeamAdapter(teams);
        rvTeams.setAdapter(adapter);
        //setContentView(R.layout.item_team);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));
        //setContentView(R.layout.activity_team_scores_5);

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
