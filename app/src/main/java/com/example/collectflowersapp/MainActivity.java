package com.example.collectflowersapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView startingTime, remainingTime, score;
    private GridLayout gridLayout;
    private ImageView flower_1,flower_2,flower_3,flower_4,flower_5,flower_6,flower_7,flower_8,flower_9;
    private ImageView[] flowerArray;
    private Runnable runnable;
    private Handler handler;
    private int currentScore = 0;
    private MediaPlayer mediaPlayer;
    private boolean soundStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        flowerArray = new ImageView[]{flower_1,flower_2,flower_3,flower_4,flower_5,flower_6,flower_7,flower_8,flower_9};
        mediaPlayer = MediaPlayer.create(this,R.raw.laugh);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                startingTime.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {

                gameControl();

                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long l) {
                        remainingTime.setText("Remaining time: " + (l/1000));
                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                        intent.putExtra("score",currentScore);
                        startActivity(intent);
                    }
                }.start();
            }
        }.start();
    }


    //inflate personalise menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_sound_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.game_menu_sound_id){
            if (!soundStatus){
                mediaPlayer.setVolume(0,0);
                item.setIcon(R.drawable.ic_volume_off);
                soundStatus = true;
            }else {
                mediaPlayer.setVolume(1,1);
                item.setIcon(R.drawable.ic_volume_on);
                soundStatus = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //init UiElements
    private void initUI () {
        startingTime  = findViewById(R.id.game_startingTime);
        remainingTime = findViewById(R.id.game_remainingTime);
        score         = findViewById(R.id.game_score);
        gridLayout    = findViewById(R.id.game_gridLayout_id);
        flower_1      = findViewById(R.id.game_1stImage);
        flower_2      = findViewById(R.id.game_2ndImage);
        flower_3      = findViewById(R.id.game_3rdImage);
        flower_4      = findViewById(R.id.game_4thImage);
        flower_5      = findViewById(R.id.game_5thImage);
        flower_6      = findViewById(R.id.game_6thImage);
        flower_7      = findViewById(R.id.game_7thImage);
        flower_8      = findViewById(R.id.game_8thImage);
        flower_9      = findViewById(R.id.game_9thImage);
    }

    private void gameControl ( ){

        startingTime.setVisibility(View.INVISIBLE);
        remainingTime.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView s: flowerArray){
                    s.setVisibility(View.INVISIBLE);
                    s.setImageResource(R.drawable.ic_flower_ic);
                    s.setEnabled(true);
                }

                gridLayout.setVisibility(View.VISIBLE);

                Random random = new Random();
                int randomFlower = random.nextInt(flowerArray.length);
                flowerArray[randomFlower].setVisibility(View.VISIBLE);

                if (currentScore < 5){
                    handler.postDelayed(runnable,2000);
                }else if (currentScore >= 5 && currentScore <= 10){
                    handler.postDelayed(runnable,1500);
                }else if (currentScore > 10){
                    handler.postDelayed(runnable,1000);
                }

            }
        };

        handler.post(runnable);
    }

    public void increaseScore (View view){
    currentScore++;
    score.setText("Your score : " + currentScore);

    if (mediaPlayer.isPlaying()){
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
    mediaPlayer.start();


    changeFlower(view.getId());


    }

    private void changeFlower (int id){

        if (id == flower_1.getId()){
            flower_1.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_1.setEnabled(false);
        }

        if (id == flower_2.getId()){
            flower_2.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_2.setEnabled(false);
        }

        if (id == flower_3.getId()){
            flower_3.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_3.setEnabled(false);
        }

        if (id == flower_4.getId()){
            flower_4.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_4.setEnabled(false);
        }

        if (id == flower_5.getId()){
            flower_5.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_5.setEnabled(false);
        }

        if (id == flower_6.getId()){
            flower_6.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_6.setEnabled(false);
        }

        if (id == flower_7.getId()){
            flower_7.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_7.setEnabled(false);
        }

        if (id == flower_8.getId()){
            flower_8.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_8.setEnabled(false);
        }

        if (id == flower_9.getId()){
            flower_9.setImageResource(R.drawable.ic_flower_clicked_ic);
            flower_9.setEnabled(false);
        }


    }
}