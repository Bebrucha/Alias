package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;

public class MainMenu_1 extends AppCompatActivity {

    public static ArrayList<Team> teams;
    public static ArrayList<Team> teamTurns;
    boolean sound_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_1);
        loadLocale();

       MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        MediaPlayer music_effect=MediaPlayer.create(this,R.raw.music_effect);

        Button button_lang = findViewById(R.id.button_lang);
        //mygtukas start
        Button Start = findViewById(R.id.button_start);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state==true)
                    sound_effect.start();
                Intent intent = new Intent(getApplicationContext(), Team_Count_3.class);
                Bundle extra = new Bundle();
                extra.putBoolean("sound", sound_state);
                intent.putExtra("extra", extra);
                startActivity(intent);
                finish();
            }
        });
        //mygtukas start

        // Change language

        //button_lang.setOnClickListener(view -> changeLanguage(button_lang));

        button_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeLanguage(button_lang);
                if(sound_state)
                    sound_effect.start();
            }
        });

        //how_to_play button action
        Button HowToPlay =findViewById(R.id.button_howtoplay);
        //HowToPlay.setOnClickListener(v -> startActivity(new Intent(MainMenu_1.this, HowToPlay_2.class)));
        HowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), HowToPlay_2.class);
                if(sound_state)
                    sound_effect.start();
                Bundle extra = new Bundle();
                extra.putBoolean("sound", sound_state);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
        String language= String.valueOf(getResources().getConfiguration().locale);
        TextView sound_off = findViewById(R.id.sounds_off_textview);
        Switch sound = findViewById(R.id.sound_effect_switch);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound_state=sound.isChecked();
                if(sound_state) {
                    if(language.equals("lt_LT")){
                        sound_effect.start();
                        sound_off.setText("Įjungti");}
                    else {
                        sound_effect.start();
                        sound_off.setText("ON");
                    }
                }
                else{
                    if(language.equals("lt_LT"))
                        sound_off.setText("Išjungti");
                    else
                    sound_off.setText("OFF");
                }
            }
        });
        TextView music_off = findViewById(R.id.music_off_textview);
        Switch music = findViewById(R.id.Music_switch);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound_state=music.isChecked();
                if(sound_state) {
                    if(language.equals("lt_LT"))
                        music_off.setText("Įjungta");
                    else
                        music_off.setText("ON");

                    music_effect.start();
                    music_effect.setLooping(true);
                }
                else {
                    if(language.equals("lt_LT"))
                        music_off.setText("Išjungta");
                    else
                    music_off.setText("OFF");

                    music_effect.pause();
                }
            }
        });




    }
    private void changeLanguage(Button button) {
        if (button.getText().equals("LT")){

            setLocale("lt");
            recreate();
        }
        else {
            setLocale("en");
            recreate();
        }
    }

    private void setLocale(String lang) {
        String country;
        if (lang.equals("lt"))
            country = "LT";
        else country = "US";

        Locale locale = new Locale(lang, country);
        Locale.setDefault(locale);
        //  Configuration config = new Configuration();
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

    public ArrayList<Team> getTeamsList(){
        return teams;
    }

}