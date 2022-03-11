package com.example.guessit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class GameSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner difficulty, choise;
    EditText p1, p2;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        p1 = (EditText)findViewById(R.id.nameP1);
        p2 = (EditText)findViewById(R.id.nameP2);
        start = (Button)findViewById(R.id.button_starter);

        difficulty = (Spinner)findViewById(R.id.difficulty_selection);
        difficulty.setOnItemSelectedListener(this);

        choise = (Spinner)findViewById(R.id.p_selection);
        choise.setOnItemSelectedListener(this);

        String [] difficulties = getResources().getStringArray(R.array.deneme);
        String [] players = getResources().getStringArray(R.array.players);


        ArrayAdapter adapter_difficulty = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, difficulties);
        adapter_difficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter_difficulty);

        ArrayAdapter adapter_choise = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, players);
        adapter_choise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choise.setAdapter(adapter_choise);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String user_difficulty = (String) difficulty.getItemAtPosition(i);
        String user_choise = (String)choise.getItemAtPosition(i);

        System.out.println("Difficulty is: " + user_difficulty);
        System.out.println("Player that starts first is: " + user_choise);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void start (View view){
        String name_1 = p1.getText().toString();
        String name_2 = p2.getText().toString();

        System.out.println("Name1: " + name_1);
        System.out.println("Name2: " + name_2);
    }
}