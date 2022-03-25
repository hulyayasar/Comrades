package com.example.guessit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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


public class GameSelection extends AppCompatActivity{


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
        difficulty.setOnItemSelectedListener(new DifficultySpinner());

        choise = (Spinner)findViewById(R.id.p_selection);
        choise.setOnItemSelectedListener(new PlayerSpinner());

        String [] difficulties = getResources().getStringArray(R.array.difficulty);
        String [] players = getResources().getStringArray(R.array.players);


        ArrayAdapter adapter_difficulty = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, difficulties);
        adapter_difficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter_difficulty);

        ArrayAdapter adapter_choise = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, players);
        adapter_choise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choise.setAdapter(adapter_choise);

        start.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(GameSelection.this);

            builder.setTitle("RULES");
            builder.setMessage("Rules are simple, the user that goes first inputs the number that that needs" +
                    "to be guessed by the second user. There is a time limit of 40 seconds. " +
                    "If you can't guessed it right before the time is over you get 0 points, " +
                    "if you guess it right you will earn 2 points for each second left on the timer. " +
                    "Good luck and have fun!");

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                name_1 = p1.getText().toString();
                name_2 = p2.getText().toString();

                String key = ref.push().getKey();
                ref.child(key).child("username").setValue(name_1);
                refLb.child(name_1).setValue(0);

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
            });
            builder.show();
        });
    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        user_difficulty = (String) difficulty.getItemAtPosition(i);
//        user_choise = (String)choise.getItemAtPosition(i);
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//    }

    class DifficultySpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            user_difficulty = (String) difficulty.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class PlayerSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            user_choise = (String) choise.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}