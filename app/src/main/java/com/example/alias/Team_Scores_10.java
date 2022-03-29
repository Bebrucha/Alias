package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Team_Scores_10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kokiaciaklase10);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);

        /// NEXT button ----------------------------------------------------------------------------
        Button next = findViewById(R.id.button_next_10);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                Intent intent;
                if(Main.game.oneTeamWon()){
                    // go to 11th
                    intent = new Intent(getApplicationContext(), Team_Scores_11.class);

                    // !!!!!! cia reikia eiti i 11-a fragmenta, bet man kazkodel uzlusta vis!!!!!!!!!!!!!!!!!
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