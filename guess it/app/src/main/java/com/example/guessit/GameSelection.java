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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GameSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Spinner difficulty, choise;
    EditText p1, p2;
    Button start;
    String user_difficulty = "";

    String user_choice = "";

    String user_choise = "";
    String name_1;
    String name_2;



    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("usernames");
    DatabaseReference refSettings = database.getReference("settings");
    DatabaseReference refLb = database.getReference("leaderboard");

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
        user_difficulty = (String) difficulty.getItemAtPosition(i);
        user_choice = (String)choise.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    public void start (View view){
        name_1 = p1.getText().toString();
        name_2 = p2.getText().toString();

        String key = ref.push().getKey();
        ref.child(key).child("username").setValue(name_1);
        refLb.child(key).child(name_1).setValue(0);

        String key1 = ref.push().getKey();
        ref.child(key1).child("username").setValue(name_2);
        refLb.child(name_2).setValue(0);

        refSettings.child("difficulty").setValue(user_difficulty);
        refSettings.child("selection").setValue(user_choise);

        Intent gameStart = new Intent(getApplicationContext(), Game.class);


        gameStart.putExtra("name1", name_1);
        gameStart.putExtra("name2", name_2);
        gameStart.putExtra("player1ID", key);
        gameStart.putExtra("player2ID", key1);

        startActivity(gameStart);

    }


}