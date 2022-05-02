package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings_4 extends AppCompatActivity {
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_4);
        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        Button Next = findViewById(R.id.button_settings_next_4);

        /// SKIP PENALTY ---------------------------------------------------------------------------
        Switch penalty = findViewById(R.id.penalty_switch);
        penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                Main.game.setIsSkipPenalty(penalty.isChecked());
            }
        });

        SeekBar seekBarLength = findViewById(R.id.seekBar_settings_round_length);
        seekBarLength.setProgress(0);
        seekBarLength.setMax(90);
        int minL = 10;
        TextView seekBarL = findViewById(R.id.textView_seek_bar_length);

        /// TIME FOR ONE ROUND seekbar -------------------------------------------------------------
        seekBarLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = minL + progress;
                seekBarL.setText(String.valueOf(value));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Main.game.setTimeOfOneRound(Integer.parseInt((String) seekBarL.getText()));
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
            }
        });

        /// NUM OF POINTS TO WIN THE GAME ----------------------------------------------------------
        SeekBar seekBarPoints = findViewById(R.id.seekBar_settings_max_points);
        seekBarPoints.setProgress(0);
        seekBarPoints.setMax(90);
        int minP = 10;

        TextView seekBarP = findViewById(R.id.textView_seek_bar_points);

        seekBarPoints.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = minP + progress;
                seekBarP.setText(String.valueOf(value));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
                Main.game.setMaxPointsToWinGame(Integer.parseInt((String) seekBarP.getText()));
            }
        });

        /// NEXT button ----------------------------------------------------------------------------
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                Intent x = new Intent(view.getContext(),Team_Scores_5.class);
                x.putExtra("timer",Integer.valueOf(seekBarL.getText().toString()));
                Bundle extra = new Bundle();
                extra.putBoolean("sound", Main.game.getIsSound());
                x.putExtra("extra", extra);
                startActivity(x);
            }
        });

//        /// PREVIOUS button ------------------------------------------------------------------------
//        Button Previous = findViewById(R.id.button_4_previous);
//        //Previous.setOnClickListener(v -> startActivity(new Intent(Settings_4.this, Team_Count_3.class)));
//        Previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(Main.game.getIsSound()){
//                    sound_effect.start();
//                }
//                Intent intent = new Intent(view.getContext(),Team_Count_3.class);
//                Bundle extra = new Bundle();
//                extra.putBoolean("sound", Main.game.getIsSound());
//                intent.putExtra("extra", extra);
//                startActivity(intent);
//            }
//        });

        /// DIFFICULTIES ---------------------------------------------------------------------------
        RadioButton junior =findViewById(R.id.Junior);
        junior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Main.game.setDifficulty(1);
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
            }
        });
        RadioButton medium =findViewById(R.id.Medium);
        medium.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Main.game.setDifficulty(2);
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
            }
        });
        RadioButton senior =findViewById(R.id.Senior);
        senior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Main.game.setDifficulty(3);
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
            }
        });

    }
}