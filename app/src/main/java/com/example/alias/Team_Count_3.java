package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Team_Count_3 extends AppCompatActivity {
    public static int num_of_teams = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_count_3);

        Bundle extra = getIntent().getBundleExtra("extra");
        boolean en_language = extra.getBoolean("language");
        System.out.println(en_language);
        System.out.println(en_language);

        //mygtukas Atgal
        Button Atgal = findViewById(R.id.button_MainMenu);
        Atgal.setOnClickListener(v -> finish());
        //mygtukas Atgal

        EditText num_of_teams_label = findViewById(R.id.num_of_teams);
        Button next_num_of_teams = findViewById(R.id.button_next_num_of_teams);

        // button next
        next_num_of_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // netrinti!!!
                // create team arrayList
                MainMenu_1.teams = Team.createTeamList(Team_Count_3.num_of_teams - 1);

                num_of_teams = Integer.parseInt(num_of_teams_label.getText().toString());
                TextView validation = findViewById(R.id.num_of_teams_validation_label);

                if(num_of_teams >= 1 && num_of_teams <= 10){
                    validation.setVisibility(View.INVISIBLE);

                    // Button to go settings page
                    Button Next = findViewById(R.id.button_next_num_of_teams);
                    Next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), Settings_4.class);
                            Bundle extra = new Bundle();
                            extra.putBoolean("language", en_language);
                            intent.putExtra("extra", extra);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else
                {
                    validation.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}