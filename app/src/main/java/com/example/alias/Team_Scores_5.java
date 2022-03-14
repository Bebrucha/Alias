package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Team_Scores_5 extends AppCompatActivity{





    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_5);
        //setContentView(R.layout.item_team);

        Button Start = findViewById(R.id.button_start_game_5);
       // Start.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, PlayingPhase_6.class)));


        Button Previous = findViewById(R.id.button_previous_5);
        Previous.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, Settings_4.class)));
        Previous.setOnClickListener(v -> finish());

        RecyclerView rvTeams = (RecyclerView) findViewById(R.id.rv_teams);

        //initiating teams and laying scores
        if(MainActivity.teams.isEmpty()) {


            MainActivity.teams = Team.createTeamList(Team_Count_3.num_of_teams - 1);
            MainActivity.teamTurns = Team.createTeamList(Team_Count_3.num_of_teams - 1);
            for(int i=0;i<MainActivity.teams.size();i++)
                MainActivity.teamTurns.get(i).mPoints=i;
        }

        TeamAdapter adapter = new TeamAdapter(MainActivity.teams);
        rvTeams.setAdapter(adapter);


        //printing which team is next
        nextTeamId();





        rvTeams.setLayoutManager(new LinearLayoutManager(this));



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
    public void nextTeamId() {
        int min_num=99999;
        for(int i=0;i<MainActivity.teamTurns.size();i++)
        {
            if(MainActivity.teamTurns.get(i).mPoints<min_num)
                min_num=MainActivity.teamTurns.get(i).mPoints;
        }
        printNextTeam(min_num);

    }
    public void printNextTeam(int min_num){
        TextView teamNameText=findViewById(R.id.textView_nextTeam);
        for(int i=0;i<MainActivity.teamTurns.size();i++)
        {
            if(MainActivity.teamTurns.get(i).mPoints==min_num)
            {
                teamNameText.setText(MainActivity.teamTurns.get(i).nameToString());
                MainActivity.teamTurns.get(i).mPoints+=Team_Count_3.num_of_teams;
            }
        }
    }


}
