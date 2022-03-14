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
        if(MainMenu_1.teams.isEmpty()) {


            MainMenu_1.teams = Team.createTeamList(Team_Count_3.num_of_teams - 1);
            MainMenu_1.teamTurns = Team.createTeamList(Team_Count_3.num_of_teams - 1);
            for(int i=0;i<MainMenu_1.teams.size();i++)
                MainMenu_1.teamTurns.get(i).mPoints=i;
        }

        TeamAdapter adapter = new TeamAdapter(MainMenu_1.teams);
        rvTeams.setAdapter(adapter);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));

        //printing which team is next
        nextTeamId();

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
    //finds lowest id
    public void nextTeamId() {
        int min_num=99999;
        for(int i=0;i<MainMenu_1.teamTurns.size();i++)
        {
            if(MainMenu_1.teamTurns.get(i).mPoints<min_num)
                min_num=MainMenu_1.teamTurns.get(i).mPoints;
        }
        printNextTeam(min_num);

    }
    //prints team with lowest id(next turn)
    public void printNextTeam(int min_num){
        TextView teamNameText=findViewById(R.id.textView_nextTeam);
        for(int i=0;i<MainMenu_1.teamTurns.size();i++)
        {
            if(MainMenu_1.teamTurns.get(i).mPoints==min_num)
            {
                teamNameText.setText(MainMenu_1.teamTurns.get(i).nameToString());
                MainMenu_1.teamTurns.get(i).mPoints+=Team_Count_3.num_of_teams;
            }
        }
    }


}
