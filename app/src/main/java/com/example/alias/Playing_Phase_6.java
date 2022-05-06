package com.example.alias;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Playing_Phase_6 extends AppCompatActivity {

    @Override
    public void onBackPressed() {

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
    TextView Time_Label ;
    TextView Last_word_textview;
    MediaPlayer sound_effect;

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

        sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        Last_word_textview=findViewById((R.id.Last_word_textview));
        Last_word_textview.setVisibility(View.INVISIBLE);
        Time_Label = findViewById(R.id.Time_Label);
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
                    word.setText("Get ready!");
                    addWordsToArrayList(game_words);
                    break;
                case 2:
                    game_words = Main.game.getWordsMedium_en();
                    word.setText("Get ready!");
                    addWordsToArrayList(game_words);
                    break;
                case 3:
                   game_words = Main.game.getWordsSenior_en();
                    word.setText("Get ready!");
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
                    word.setText("Pasiruoškite!");
                    addWordsToArrayList(game_words);
                    break;
                case 2:
                    game_words = Main.game.getWordsMedium_lt();
                    word.setText("Pasiruoškite!");
                    addWordsToArrayList(game_words);
                    break;
                case 3:
                    game_words = Main.game.getWordsSenior_lt();
                    word.setText("Pasiruoškite!");
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
                    else{
                        word.setText("OutOfWords:(");
                        button_guessed.setEnabled(false);
                    }
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
                    else{
                        word.setText("OutOfWords:(");
                        button_skipped.setEnabled(false);
                    }

                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), Team_Scores_10.class);
                    startActivity(intent);
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
                button_guessed.setEnabled(true);
                button_skipped.setEnabled(true);
                continue2.setVisibility(View.INVISIBLE);
                stop_button.setVisibility(View.VISIBLE);
            }
        });

    }
    //timer end

    //timer starts counting/resumes counting
    private void startCDTimer(){

        timerIsFinished = false;
       cdTimer= new CountDownTimer(timer*1000+50, 1000) {

            public void onTick(long millisUntilFinished) {
                Timer.setText(" "+millisUntilFinished / 1000+" ");
                Start_Timer.setVisibility(View.INVISIBLE);
                timer=millisUntilFinished/1000;
            }
            public void onFinish() {
                if(!Main.game.getIsEnglish())
                    Last_word_textview.setText("Paskutinis\nžodis!");
                else
                    Last_word_textview.setText("Last word!");
                Last_word_textview.setVisibility(View.VISIBLE);
                Button stop_button =findViewById(R.id.button_stop);
                stop_button.setEnabled(false);
                stop_button.setVisibility(View.INVISIBLE);
                Time_Label.setVisibility(View.INVISIBLE);
                Timer.setVisibility(View.INVISIBLE);
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
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }
                String team_guessed_word = spinner_teamNames.getSelectedItem().toString();
                for (int i = 0; i < Main.game.getNumOfTeams(); i++)
                    if(team_guessed_word.equals(Main.game.getTeam(i).getName()))
                        Main.game.getTeam(i).updatePoints(1);

                Intent intent = new Intent(getApplicationContext(), Team_Scores_10.class);
                startActivity(intent);
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