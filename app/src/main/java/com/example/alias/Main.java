package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class Main extends AppCompatActivity {

    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    // --------main game variable ----- public, thus we can use it everywhere----
    public static Game game = new Game();
    //--------------------------------------------------------------------------
    MediaPlayer music_effect;

    @Override
    protected void onPause() {
        super.onPause();
        if (music_effect != null){
            music_effect.pause();
            if (isFinishing()){
                music_effect.pause();
            }
        }
        Context context = getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if (!taskInfo.isEmpty()) {
            ComponentName topActivity = taskInfo.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                music_effect.pause();
            }
        }
        super.onPause();
    }
    @Override
    protected void onResume() {
        if(music_effect != null && !music_effect.isPlaying() && game.getIsMusic())
            music_effect.start();
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_1);
        loadLocale();

        if(game.getGameCreatedForTheFirstTime()){
            setLocale("lt");
            game.setGameCreatedForTheFirstTime(false);
        }


       MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
         music_effect=MediaPlayer.create(this,R.raw.music_effect);
        game.setMusic(music_effect);

        // START button ----------------------------------------------------------------------------
        Button Start = findViewById(R.id.button_start);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(game.getIsSound() == true)
                    sound_effect.start();
                Intent intent = new Intent(getApplicationContext(), Team_Count_3.class);
                Bundle extra = new Bundle();
                extra.putBoolean("sound", game.getIsSound());
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });


        // LANGUAGE button -------------------------------------------------------------------------
        Button button_lang = findViewById(R.id.button_lang);
        if (button_lang.getText().equals("LT")){
            button_lang.setBackground(this.getResources().getDrawable(R.drawable.lt_flag));
        }
        else {
            button_lang.setBackground(this.getResources().getDrawable(R.drawable.uk_flag));
        }

        // Patikrina dabartine kalba pagal nustatyta Locale
        String currentLanguage = Locale.getDefault().getDisplayLanguage();
        game.setLanguage(currentLanguage.toLowerCase().contains("en"));

        button_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(game.getIsSound())
                    sound_effect.start();
                changeLanguage(button_lang);
            }
        });


        // HOW TO PLAY button ----------------------------------------------------------------------
        Button HowToPlay =findViewById(R.id.button_howtoplay);

        HowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), How_To_Play_2.class);
                if(game.getIsSound())
                    sound_effect.start();
                Bundle extra = new Bundle();
                extra.putBoolean("music", game.getIsSound());
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
        String language= String.valueOf(getResources().getConfiguration().locale);

        // SOUND switch ----------------------------------------------------------------------------
        TextView sound_off = findViewById(R.id.sounds_off_textview);
        Switch sound = findViewById(R.id.sound_effect_switch);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setIsSound(sound.isChecked());
                if(game.getIsSound()) {
                    if(game.getIsEnglish()){
                        sound_effect.start();
                        sound_off.setText("ON");}
                    else {
                        sound_effect.start();
                        sound_off.setText("Įjungta");
                    }
                }
                else{
                    if(game.getIsEnglish())
                        sound_off.setText("OFF");
                    else
                    sound_off.setText("Išjungta");
                }
            }
        });
        // MORE button ----------------------------------------------------------------------
        Button More =findViewById(R.id.button_More);

        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), More.class);
                if(game.getIsSound())
                    sound_effect.start();
                startActivity(intent);
            }
        });


        // MUSIC switch ----------------------------------------------------------------------------
        TextView music_off = findViewById(R.id.music_off_textview);
        Switch music = findViewById(R.id.Music_switch);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setIsMusic(music.isChecked());
                if(game.getIsMusic()) {
                    if(game.getIsEnglish())
                        music_off.setText("ON");
                    else
                        music_off.setText("Įjungta");
                    recreate();
                    music_effect.start();
                    music_effect.setLooping(true);

                }
                else {
                    if(game.getIsEnglish())
                        music_off.setText("OFF");
                    else
                    music_off.setText("Išjungta");

                    music_effect.pause();
                    recreate();
                }
            }
        });
        if(music_effect.isPlaying()){
            music.setChecked(true);
        }
        if(game.getIsMusic()){
            music.setChecked(true);
        }


        if(game.getIsMusic()) {
            if(game.getIsEnglish())
                music_off.setText("ON");
            else
                music_off.setText("Įjungta");

        }
        else {
            if(game.getIsEnglish())
                music_off.setText("OFF");
            else
                music_off.setText("Išjungta");
        }

        if(game.getIsSound()) {
            if(game.getIsEnglish())
                sound_off.setText("ON");
            else
                sound_off.setText("Įjungta");

        }
        else {
            if(game.getIsEnglish())
                sound_off.setText("OFF");
            else
                sound_off.setText("Išjungta");
        }

    }

    private void changeLanguage(Button button) {
        if (button.getText().equals("EN")){

            setLocale("lt");
            game.setLanguage(false);
            button.setBackground(this.getResources().getDrawable(R.drawable.uk_flag));
            recreate();
        }
        else {
            setLocale("en");
            game.setLanguage(true);
            button.setBackground(this.getResources().getDrawable(R.drawable.lt_flag));
            recreate();
        }
    }

    private void setLocale(String lang) {
        String country;
        if (lang.equals("lt")) {
            country = "LT";
        }
        else
        {
            country = "US";
        }
        Locale locale = new Locale(lang, country);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}