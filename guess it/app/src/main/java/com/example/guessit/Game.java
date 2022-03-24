package com.example.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity{

    TextView p1;
    TextView p2;
    Button button;
    String name1, name2, id1, id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        p1 = (TextView) findViewById(R.id.deneme1);
        p2 = (TextView) findViewById(R.id.deneme2);
        button = (Button) findViewById(R.id.denemeButton);

        //names will be used to display it on the gamescreen
        name1 = getIntent().getExtras().get("name1").toString();
        name2 = getIntent().getExtras().get("name2").toString();

        //id's will be used to update the database with the corresponding points
        id1 = getIntent().getExtras().get("player1ID").toString();
        id2 = getIntent().getExtras().get("player2ID").toString();

        //display previous guesses in a container
        //10 tries maximum

        //easy mode: 0-50
        //medium mode: 0-100
        //hard mode: 0-150

    }

    public void game (View view){
        p1.setText(name1);
        p2.setText(id1);
    }

}