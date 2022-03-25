package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToPlay_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoplay_2);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        Bundle extra = getIntent().getBundleExtra("extra");
        boolean sound_state = extra.getBoolean("sound");

        Button HowToPlay_To_Start =findViewById(R.id.button_howtoplay_to_start);
       // HowToPlay_To_Start.setOnClickListener(v -> startActivity(new Intent(HowToPlay_2.this, MainMenu_1.class)));
        //HowToPlay_To_Start.setOnClickListener(v -> finish());
        HowToPlay_To_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sound_state)
                    sound_effect.start();
                finish();
            }
        });

    }
}