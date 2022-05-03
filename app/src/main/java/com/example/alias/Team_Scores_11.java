package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Team_Scores_11 extends AppCompatActivity {

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
        setContentView(R.layout.activity_team_scores11);
        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        TextView label_winner_team = findViewById(R.id.label_winner_announcement_11);
        label_winner_team.setText(Main.game.getWinnerTeam().getName());

        Button main_menu = findViewById(R.id.button_play_again);

        Main.game.sortTeamsByPoints();
        //recyclerview of teams
        RecyclerView rvTeams = (RecyclerView) findViewById(R.id.rv_teams2);
        Team_Adapter adapter = new Team_Adapter(Main.game.getAll_teams(), false);
        rvTeams.setAdapter(adapter);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));

        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
                Intent intent = new Intent(getApplicationContext(), Main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}