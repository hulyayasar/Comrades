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

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class GameSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner difficulty, choise;
    EditText p1, p2;
    Button start;
    String user_difficulty = "";
    String user_choise = "";

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

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
        user_choise = (String)choise.getItemAtPosition(i);

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

        ref.child("usernames/user1").setValue(name_1);
        ref.child("usernames/user2").setValue(name_2);
        ref.child("settings/difficulty").setValue(user_difficulty);
        ref.child("settings/selection").setValue(user_choise);

    }

}