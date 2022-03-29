package com.example.alias;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;
import java.util.ArrayList;

public class Playing_Phase_6 extends AppCompatActivity {

    CountDownTimer cdTimer;
    TextView Timer;
    long timer=0;
    Button Start_Timer;
    Button continue2;
    Button button_guessed;
    Button button_skipped;
    int guessed_amount=0;
    int skipped_amount=0;
    TextView label_num_of_guessed_words;
    TextView label_num_of_skipped_words;
    boolean timerIsFinished =false;
    String []game_words;
    Random random=new Random();
    ArrayList<String> game_word_list=new ArrayList<String>();

    // -----------Alert Dialogue -- 9 fragment
    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialogue;
    private Spinner spinner_teamNames;
    private Button button_save;
    // ----------- Alert Dialogue-PopUp Window

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_phase_6);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);

        Bundle timeris = getIntent().getExtras();
        timer=timeris.getInt("timer");
        Timer=findViewById(R.id.timer);
        Timer.setText(String.valueOf(timer));

        /// Write the name of currently playing team
        TextView label_currently_playing_team = findViewById(R.id.label_name_of_currently_playing_team_6);
        label_currently_playing_team.setText(Main.game.getCurrentlyPlayingTeam().getName());

        // Select words by language and difficulty
        TextView word=findViewById(R.id.word_to_display_6);
        if(Main.game.getIsEnglish())
        {
            switch (Main.game.getDifficulty())
            {
                case 1:
                   game_words = Main.game.getWordsJunior_en();
                    word.setText("EN - junior");
                    addWordsToArrayList(game_words);
                    break;
                case 2:
                    game_words = Main.game.getWordsMedium_en();
                    word.setText("EN - medium");
                    addWordsToArrayList(game_words);
                    break;
                case 3:
                   game_words = Main.game.getWordsSenior_en();
                    word.setText("EN - senior");
                    addWordsToArrayList(game_words);
                    break;
            }
        }
        else
        {
            switch (Main.game.getDifficulty())
            {
                case 1:
                    game_words = Main.game.getWordsJunior_lt();
                    word.setText("LT - junior");
                    addWordsToArrayList(game_words);
                    break;
                case 2:
                    game_words = Main.game.getWordsMedium_lt();
                    word.setText("LT - medium");
                    addWordsToArrayList(game_words);
                    break;
                case 3:
                    game_words = Main.game.getWordsSenior_lt();
                    word.setText("LT - senior");
                    addWordsToArrayList(game_words);
                    break;
            }
        }

        // GUESSED button --------------------------------------------------------------------------
        label_num_of_guessed_words = findViewById(R.id.label_num_of_guessed_words_6);
        button_guessed = findViewById(R.id.button_guessed);
        button_guessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                if (!timerIsFinished){
                    guessed_amount++;
                    label_num_of_guessed_words.setText(Integer.toString(guessed_amount));
                    Main.game.getCurrentlyPlayingTeam().updatePoints(1);

                    if(game_word_list.size() > 0)
                    {
                        int random_num = random.nextInt(game_word_list.size());
                        word.setText(game_word_list.get(random_num));
                        game_word_list.remove(random_num);
                    }
                    else word.setText("OutOfWords:(");
                }
                else
                {
                    createAlertDialogue();
                }

            }
        });

        // SKIPPED button --------------------------------------------------------------------------
        label_num_of_skipped_words = findViewById(R.id.label_num_of_skipped_words_6);
        button_skipped = findViewById(R.id.button_skipped);
        button_skipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                if(!timerIsFinished){
                    skipped_amount++;
                    label_num_of_skipped_words.setText(Integer.toString(skipped_amount));

                    if(Main.game.getIsSkipPenalty())
                        Main.game.getCurrentlyPlayingTeam().updatePoints(-1);

                    if(game_word_list.size() > 0)
                    {
                        int random_num = random.nextInt(game_word_list.size());
                        word.setText(game_word_list.get(random_num));
                        game_word_list.remove(random_num);
                    }
                    else word.setText("OutOfWords:(");

                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), Team_Scores_10.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        button_guessed.setEnabled(false);
        button_skipped.setEnabled(false);

        // START TIMER button ----------------------------------------------------------------------
        Start_Timer = findViewById(R.id.Start_True);
        Start_Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                if(game_word_list.size()>0)
                {
                    int random_num = random.nextInt(game_word_list.size());
                    word.setText(game_word_list.get(random_num));
                    game_word_list.remove(random_num);
                }
                else word.setText("OutOfWords:(");

                //enabling button "stop"
                ((Button) findViewById(R.id.button_stop)).setEnabled(true);
                button_guessed.setEnabled(true);
                button_skipped.setEnabled(true);
                Start_Timer.setEnabled(false);
                startCDTimer();
            }
        });
        //pabaiga
        //Bundle bundle = new Bundle();
        //int timer = bundle.getInt(timer);
        //timer start
       // new CountDownTimer(timer*1000, 1000) { // 1 - sekundziu skaicius, 2- mazejimo zingsnis


        // STOP button -----------------------------------------------------------------------------
        Button stop_button =findViewById(R.id.button_stop);
        stop_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(Main.game.getIsSound()){
                sound_effect.start();
            }
            stopCDTimer();
            //Intent intent = new Intent(PlayingPhase_6.this, StoppedPhase_7.class);
            //startActivity(intent);

            //continue2=(Button) findViewById(R.id.button_continue2);
            continue2.setVisibility(View.VISIBLE);
            stop_button.setVisibility(View.INVISIBLE);
            button_guessed.setEnabled(false);
            button_skipped.setEnabled(false);
        }
        });

        // CONTINUE button "continue" to resume timer ----------------------------------------------
        continue2=(Button) findViewById(R.id.button_continue2);
        continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                startCDTimer();
                //Intent intent = new Intent(PlayingPhase_6.this, StoppedPhase_7.class);
                //startActivity(intent);
                button_guessed.setEnabled(true);
                button_skipped.setEnabled(true);
                //continue2=(Button) findViewById(R.id.button_continue2);
                continue2.setVisibility(View.INVISIBLE);
                stop_button.setVisibility(View.VISIBLE);
            }
        });

    }
    //timer end

    //timer starts counting/resumes counting
    private void startCDTimer(){

        String language= String.valueOf(getResources().getConfiguration().locale);
        timerIsFinished = false;
       cdTimer= new CountDownTimer(timer*1000+50, 1000) {

            public void onTick(long millisUntilFinished) {
                Timer.setText(" "+millisUntilFinished / 1000+" ");
                Start_Timer.setVisibility(View.INVISIBLE);
                timer=millisUntilFinished/1000;
            }
            public void onFinish() {
                if(language.equals("lt_LT"))
                    Timer.setText("Paskutinis\nžodis!");
                else
                    Timer.setText("Last word!");

//                team_points=guessed_amount-skipped_amount;
//                if(team_points<0){
//                    team_points=0;
//                }
                Button stop_button =findViewById(R.id.button_stop);
                stop_button.setEnabled(false);
                timerIsFinished = true;
            }

        }.start();
    }

    //timer is stopped
    private void stopCDTimer(){

        timerIsFinished = false;
        cdTimer.cancel();

    }

    public void createAlertDialogue(){
        dialogueBuilder = new AlertDialog.Builder(this);
        final View teamsPopUpView = getLayoutInflater().inflate(R.layout.popup_window_9, null);
        spinner_teamNames = (Spinner) teamsPopUpView.findViewById(R.id.spinner_team_guessed_9);
        button_save = (Button) teamsPopUpView.findViewById(R.id.button_save_9);

        dialogueBuilder.setView(teamsPopUpView);
        dialogue = dialogueBuilder.create();
        dialogue.show();

        ArrayList<String> spinnerArray = new ArrayList<>();
        for(int i = 0; i < Main.game.getNumOfTeams(); i++)
            spinnerArray.add(Main.game.getTeam(i).getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray
        );

        spinner_teamNames.setAdapter(adapter);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Find the team by its' name and add give a point
                String team_guessed_word = spinner_teamNames.getSelectedItem().toString();
                for (int i = 0; i < Main.game.getNumOfTeams(); i++)
                    if(team_guessed_word.equals(Main.game.getTeam(i).getName()))
                        Main.game.getTeam(i).updatePoints(1);

                Intent intent = new Intent(getApplicationContext(), Team_Scores_10.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void addWordsToArrayList(String[]words)
    {
        for(int i = 0; i < words.length; i++)
        {
            game_word_list.add(words[i]);
        }
    }

}