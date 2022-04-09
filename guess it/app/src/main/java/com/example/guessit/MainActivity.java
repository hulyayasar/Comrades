package com.example.guessit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Button that leads to the leaderboard page
    public void leaderboard(View view){
        Intent leaderboardIntent = new Intent(getApplicationContext(), Leaderboard.class);
        startActivity(leaderboardIntent);
    }

    //Button that leads to the game page
    public void gameInitialize(View view){
        Intent gameIntent = new Intent(getApplicationContext(), GameSelection.class);
        startActivity(gameIntent);
    }

}