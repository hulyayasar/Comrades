package com.example.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity{

    String name1, name2, id1, id2, difficulty_selection;
    int difficulty_selection_int;
    TextView counter, difficulty_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        counter = (TextView) findViewById(R.id.counter);
        difficulty_show = (TextView) findViewById(R.id.difficulty_show);

        //getting difficulty selection from game selection class
        difficulty_selection = getIntent().getExtras().get("difficulty").toString();
        difficulty_selection_int = Integer.parseInt(difficulty_selection);

        //names will be used to display it on the gamescreen
        name1 = getIntent().getExtras().get("name1").toString();
        name2 = getIntent().getExtras().get("name2").toString();

        //id's will be used to update the database with the corresponding points
        id1 = getIntent().getExtras().get("player1ID").toString();
        id2 = getIntent().getExtras().get("player2ID").toString();

        //display previous guesses in a container



        if(difficulty_selection_int == 0){
            easy_mode();
        }

        else if(difficulty_selection_int == 1){
            normal_mode();
        }

        else if(difficulty_selection_int == 2){
            hard_mode();
        }
        //easy mode: 0-50
        //medium mode: 0-100
        //hard mode: 0-150

    }

    public void easy_mode(){
        difficulty_show.setText("Difficulty: Easy");
        new CountDownTimer(100000, 1000) {
            public void onTick(long millisUntilFinished) {
                counter.setText("Time remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter.setText("done!");
            }
        }.start();
    }

    public void normal_mode(){
        difficulty_show.setText("Difficulty: Medium");
        new CountDownTimer(80000, 1000) {
            public void onTick(long millisUntilFinished) {
                counter.setText("Time remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter.setText("done!");
            }
        }.start();
    }

    public void hard_mode(){
        difficulty_show.setText("Difficulty: Hard");
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                counter.setText("Time remaining: " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                counter.setText("done!");
            }
        }.start();
    }
}
