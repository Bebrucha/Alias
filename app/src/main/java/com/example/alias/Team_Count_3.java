package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Team_Count_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_count_3);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);

//        /// PREVIOUS button ------------------------------------------------------------------------
//        Button previous = findViewById(R.id.button_MainMenu);
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(Main.game.getIsSound())
//                    sound_effect.start();
//                Intent intent = new Intent(getApplicationContext(), Main.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        EditText num_of_teams_label = findViewById(R.id.num_of_teams);
        Button next_num_of_teams = findViewById(R.id.button_next_num_of_teams);

        /// NEXT button ----------------------------------------------------------------------------
        next_num_of_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
                // netrinti!!!
                Main.game.setTeams(Integer.parseInt(num_of_teams_label.getText().toString()));

                TextView validation = findViewById(R.id.num_of_teams_validation_label);

                if(Main.game.getNumOfTeams() >= 1 && Main.game.getNumOfTeams() <= 10){
                    validation.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(getApplicationContext(), Settings_4.class);
                    Bundle extra = new Bundle();
                    extra.putBoolean("sound", Main.game.getIsSound());
                    intent.putExtra("extra", extra);
                    startActivity(intent);
                    finish();

                } else
                {
                    validation.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}