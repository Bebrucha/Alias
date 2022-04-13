package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Team_Scores_10 extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kokiaciaklase10);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);


        RecyclerView rvTeams = (RecyclerView) findViewById(R.id.rv_teams_10);
        Team_Adapter adapter = new Team_Adapter(Main.game.getAll_teams(),true);
        rvTeams.setAdapter(adapter);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));
        /// NEXT button ----------------------------------------------------------------------------
        Button next = findViewById(R.id.button_next_10);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                Intent intent;
                if(Main.game.oneTeamWon() && Main.game.everyTeamPlayedInThisRound()){
                    // go to 11th
                    intent = new Intent(getApplicationContext(), Team_Scores_11.class);
                }
                else
                {
                    // go back to the 5th
                    intent = new Intent(getApplicationContext(), Team_Scores_5.class);

                }

                Bundle extra = new Bundle();
                extra.putBoolean("sound", Main.game.getIsSound());
                intent.putExtra("extra", extra);
                startActivity(intent);
                finish();

            }
        });

    }

}