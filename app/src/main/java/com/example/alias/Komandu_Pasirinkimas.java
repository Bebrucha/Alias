package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Komandu_Pasirinkimas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komandu_pasirinkimas);

        //mygtukas Atgal
        Button Atgal = findViewById(R.id.button_MainMenu);
        Atgal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        //mygtukas Atgal
    }
}