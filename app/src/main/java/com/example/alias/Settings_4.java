package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Settings_4 extends AppCompatActivity {

    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_4);

        Button Next = findViewById(R.id.button_settings_next_4);
        Next.setOnClickListener(v -> startActivity(new Intent(Settings_4.this, Team_Scores_5.class)));

        SeekBar seekBarLength = findViewById(R.id.seekBar_settings_round_length);
        seekBarLength.setProgress(0);
        seekBarLength.setMax(100);
        TextView seekBarL = findViewById(R.id.textView_seek_bar_length);

        //Laiko vienas ejimui seekbaras
        seekBarLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarL.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



        //Maximumo tasku seekbaras
        SeekBar seekBarPoints = findViewById(R.id.seekBar_settings_max_points);
        seekBarPoints.setProgress(0);
        seekBarPoints.setMax(100);
        TextView seekBarP = findViewById(R.id.textView_seek_bar_points);

        seekBarPoints.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarP.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}