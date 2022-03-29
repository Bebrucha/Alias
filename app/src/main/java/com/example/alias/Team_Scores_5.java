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

public class Team_Scores_5 extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_5);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);

        // PREVIOUS button -------------------------------------------------------------------------
        Button Previous = findViewById(R.id.button_previous_5);
        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
                Intent intent = new Intent(view.getContext(), Settings_4.class);
                Bundle extra = new Bundle();
                extra.putBoolean("sound", Main.game.getIsSound());
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });


        RecyclerView rvTeams = (RecyclerView) findViewById(R.id.rv_teams);

        Team_Adapter adapter = new Team_Adapter(Main.game.getAll_teams());
        rvTeams.setAdapter(adapter);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));


        // -----------------------------------------------------------------------------------------
        Main.game.updateCurrentRoundNum(); // ---------------------- veeeery important method
        // -----------------------------------------------------------------------------------------

        TextView label_next_team = findViewById(R.id.textView_nextTeam);
        label_next_team.setText(Main.game.getCurrentlyPlayingTeam().getName());

        // START button ----------------------------------------------------------------------------
        Button Start = findViewById(R.id.button_start_game_5);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                Intent x = new Intent(view.getContext(), Playing_Phase_6.class);
                Bundle extras = getIntent().getExtras();
                x.putExtra("timer",extras.getInt("timer"));
                Bundle extra = new Bundle();
                extra.putBoolean("sound", Main.game.getIsSound());
                x.putExtra("extra", extra);
                startActivity(x);
            }
        });

    }
}
