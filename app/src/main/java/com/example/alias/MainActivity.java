package com.example.alias;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private com.example.alias.databinding.ActivityMainBinding binding;

    public static ArrayList<Team> teams;
    public static ArrayList<Team> teamTurns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();



        binding = com.example.alias.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getResources().getString(R.string.app_name));



        //mygtukas start
        Button Start = findViewById(R.id.button_start);
        Start.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Team_Count_3.class)));
        //mygtukas start
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Change language
        Button button_lang = findViewById(R.id.button_lang);
        button_lang.setOnClickListener(view -> changeLanguage(button_lang));

        //how_to_play button action
        Button HowToPlay =findViewById(R.id.button_howtoplay);
        HowToPlay.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HowToPlay_2.class)));


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
