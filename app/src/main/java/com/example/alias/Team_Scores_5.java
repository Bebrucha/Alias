package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class Team_Scores_5 extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_5);
        //setContentView(R.layout.item_team);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        Bundle extra = getIntent().getBundleExtra("extra");
        boolean sound_state = extra.getBoolean("sound");

        Button Start = findViewById(R.id.button_start_game_5);
       // Start.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, PlayingPhase_6.class)));


        Button Previous = findViewById(R.id.button_previous_5);

       // Previous.setOnClickListener(v -> startActivity(new Intent(Team_Scores_5.this, Settings_4.class)));
       // Previous.setOnClickListener(v -> finish());
        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                Intent intent = new Intent(view.getContext(), Settings_4.class);
                Bundle extra = new Bundle();
                extra.putBoolean("sound", sound_state);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });

        // End_game button
        //Button end_game = findViewById(R.id.button_end_game_5);
       // end_game.setOnClickListener(v -> startActivity(new Intent(v.getContext(), Team_Scores_11.class)));

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
                if(sound_state){
                    sound_effect.start();
                }
                Intent x = new Intent(view.getContext(),PlayingPhase_6.class);
                Bundle extras = getIntent().getExtras();
                x.putExtra("timer",extras.getInt("timer"));
                Bundle extra = new Bundle();
                extra.putBoolean("sound", sound_state);
                x.putExtra("extra", extra);
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
