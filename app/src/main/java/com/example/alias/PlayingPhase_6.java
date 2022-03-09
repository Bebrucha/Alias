package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class PlayingPhase_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_phase_6);
        Bundle timeris = getIntent().getExtras();
        int timer;
        timer=timeris.getInt("timer");
        TextView Timer=findViewById(R.id.timer);
        //mygtukas pradeti timer
        Button Start_Timer = findViewById(R.id.Start_True);
        Start_Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start_Timer.setEnabled(false);
                new CountDownTimer(timer*1000+50, 1000) {
                    public void onTick(long millisUntilFinished) {
                        Timer.setText(" "+millisUntilFinished / 1000+" ");
                        Start_Timer.setVisibility(View.INVISIBLE);
                    }

                    public void onFinish() {
                        Timer.setText("Time is up!");
                    }
                }.start();
            }
        });
        //pabaiga
        //Bundle bundle = new Bundle();
        //int timer = bundle.getInt(timer);
        //timer start
       // new CountDownTimer(timer*1000, 1000) { // 1 - sekundziu skaicius, 2- mazejimo zingsnis

    }
    //timer end

}