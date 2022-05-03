package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class How_To_Play_2 extends AppCompatActivity {
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (Main.game.getMusic() != null){
            Main.game.getMusic().pause();
            if (isFinishing()){
                Main.game.getMusic().pause();
            }
        }
        Context context = getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if (!taskInfo.isEmpty()) {
            ComponentName topActivity = taskInfo.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                Main.game.getMusic().pause();
            }
        }
        super.onPause();
    }
    @Override
    protected void onResume() {

        if(!Main.game.getIsMusic()){
            Main.game.getMusic().stop();
        }
        if(Main.game.getMusic() != null && !Main.game.getMusic().isPlaying() && Main.game.getIsMusic());
        Main.game.getMusic().start();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoplay_2);


//        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);

//        Button HowToPlay_To_Start =findViewById(R.id.button_howtoplay_to_start);
//
//        HowToPlay_To_Start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(Main.game.getIsSound())
//                    sound_effect.start();
//            }
//        });

    }
}