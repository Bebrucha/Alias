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

import java.util.ArrayList;
import java.util.Locale;

public class MainMenu_1 extends AppCompatActivity {

    public static ArrayList<Team> teams;
    public static ArrayList<Team> teamTurns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_1);
        loadLocale();


      //  boolean sound_state=false;
      //  MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);

        Button button_lang = findViewById(R.id.button_lang);
        //mygtukas start
        Button Start = findViewById(R.id.button_start);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Team_Count_3.class);
              //  Bundle extra = new Bundle();
             //   extra.putBoolean("sound", sound_state);
               // intent.putExtra("extra", extra);
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
            }
        });

        //how_to_play button action
        Button HowToPlay =findViewById(R.id.button_howtoplay);
        HowToPlay.setOnClickListener(v -> startActivity(new Intent(MainMenu_1.this, HowToPlay_2.class)));

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
}