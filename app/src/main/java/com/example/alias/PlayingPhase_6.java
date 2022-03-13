package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class PlayingPhase_6 extends AppCompatActivity {
    //timer variables
    CountDownTimer cdTimer;
    //long timeLeftInMillis;
    TextView Timer;
    long timer=0;
    Button Start_Timer;
    Button continue2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_phase_6);


        Bundle timeris = getIntent().getExtras();
        //int timer;
        timer=timeris.getInt("timer");
        Timer=findViewById(R.id.timer);
        Timer.setText(String.valueOf(timer));
        //mygtukas pradeti timer
        Start_Timer = findViewById(R.id.Start_True);
        Start_Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //enabling button "stop"
                ((Button) findViewById(R.id.button_stop)).setEnabled(true);

                Start_Timer.setEnabled(false);
                startCDTimer();
            }
        });
        //pabaiga
        //Bundle bundle = new Bundle();
        //int timer = bundle.getInt(timer);
        //timer start
       // new CountDownTimer(timer*1000, 1000) { // 1 - sekundziu skaicius, 2- mazejimo zingsnis



        //STOP mygtukas
        Button stop_button =findViewById(R.id.button_stop);
        //Continue button
        continue2=(Button) findViewById(R.id.button_continue2);

        stop_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            stopCDTimer();
            //Intent intent = new Intent(PlayingPhase_6.this, StoppedPhase_7.class);
            //startActivity(intent);

            //continue2=(Button) findViewById(R.id.button_continue2);
            continue2.setVisibility(View.VISIBLE);
            stop_button.setVisibility(View.INVISIBLE);
        }
        });

        //button "continue" to resume timer
        continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCDTimer();
                //Intent intent = new Intent(PlayingPhase_6.this, StoppedPhase_7.class);
                //startActivity(intent);

                //continue2=(Button) findViewById(R.id.button_continue2);
                continue2.setVisibility(View.INVISIBLE);
                stop_button.setVisibility(View.VISIBLE);
            }
        });

    }
    //timer end

    //timer starts counting/resumes counting
    private void startCDTimer(){

       cdTimer= new CountDownTimer(timer*1000+50, 1000) {

            public void onTick(long millisUntilFinished) {
                Timer.setText(" "+millisUntilFinished / 1000+" ");
                Start_Timer.setVisibility(View.INVISIBLE);
                timer=millisUntilFinished/1000;
            }
            public void onFinish() { Timer.setText("Time is up!");
            }

        }.start();
    }

    //timer is stopped
    private void stopCDTimer(){

        cdTimer.cancel();

    }

}