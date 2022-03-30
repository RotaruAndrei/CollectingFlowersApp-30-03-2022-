package com.example.collectflowersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView congratulations, endScore, highestScore;
    private Button playAgain_btn, quitGame_btn;
    private int userScore;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initUI();

        userScore = getIntent().getIntExtra("score",-1);
        endScore.setText("Your final score: " + userScore);

        sharedPreferences = getSharedPreferences("Score",MODE_PRIVATE);
        int highestSaved  = sharedPreferences.getInt("highestScore",0);

        if (userScore > highestSaved){
            sharedPreferences.edit().putInt("highestScore",userScore).apply();
            highestScore.setText("Highest score: " + userScore);
            congratulations.setText("Congratulations, you have score a new high record");
        }

        highestScore.setText("Highest score: " + highestSaved);

        congratulationsMessage(highestSaved - userScore);

        quitGame_btn.setOnClickListener(onClick ->{
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });

        playAgain_btn.setOnClickListener(onClick -> {
            startActivity(new Intent(ResultActivity.this,MainActivity.class));
            finish();
        });


    }


    private void initUI (){
        congratulations = findViewById(R.id.result_congratulations);
        endScore        = findViewById(R.id.result_endScore);
        highestScore    = findViewById(R.id.result_highestScore);
        playAgain_btn   = findViewById(R.id.result_playAgain_btn);
        quitGame_btn    = findViewById(R.id.result_quitGame_btn);
    }

    private void congratulationsMessage (int value) {

        if (value > 5){
            congratulations.setText("Don't give up, you can improve!");
        }else if (value > 2 && value < 6){
            congratulations.setText("You are getting better and better !");
        }else if (value < 3){
            congratulations.setText("You almost done it !!");
        }
    }
}