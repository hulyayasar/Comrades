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
    int difficulty_index;

    String user_choise = "";

    String name_1;
    String name_2;


    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference refLb = database.getReference("leaderboard"); //initialize database

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


        //used spinner dropdownmenu to for the selection process
        ArrayAdapter adapter_difficulty = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, difficulties);
        adapter_difficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter_difficulty);

        ArrayAdapter adapter_choise = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, players);
        adapter_choise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choise.setAdapter(adapter_choise);


        //when start button is start
        start.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(GameSelection.this);


            //rules will change depending on which difficulty was selected by the user
            switch (difficulty_index){
                //Easy: 100 seconds, 3 points for each second left. Number selection between 0-50.
                // 3 seconds wait time between each guess

                case 0:
                    builder.setTitle("RULES");
                    builder.setMessage("Rules are simple, the player that you have chosen goes first" +
                            " Your goal is to guess the number correctly" +
                            " You have selected Easy mode, so here are the rules for it: " +
                            "You will have 100 seconds to guess the number correctly. For each second left you will get" +
                            " 3 points. The range of selection is from 0 to 50." +
                            " Good Luck!");

                    builder.setPositiveButton("OK", (dialogInterface, i) -> {

                        //we get the names entered
                        name_1 = p1.getText().toString();
                        name_2 = p2.getText().toString();

                        //we set those usernames with default points (zero)
                        refLb.child(name_1).setValue(0);
                        refLb.child(name_2).setValue(0);


                        Intent gameStart = new Intent(getApplicationContext(), Game.class);


                        //we pass the necessary information to next page
                        gameStart.putExtra("name1", name_1);
                        gameStart.putExtra("name2", name_2);
                        gameStart.putExtra("difficulty", difficulty_index);
                        gameStart.putExtra("user", user_choise);

                        //starts the game page
                        startActivity(gameStart);
                    });
                    builder.show();
                    break;

                case 1:
                    //Medium: 80 seconds, 6 points for each second left. Number selection between 0-100.
                    //3 seconds wait time between each guess

                    builder.setTitle("RULES");
                    builder.setMessage("Rules are simple, the player that you have chosen goes first" +
                            " Your goal is to guess the number correctly" +
                            " You have selected Normal mode, so here are the rules for it: " +
                            "You will have 80 seconds to guess the number correctly. For each second left you will get" +
                            " 6 points. The range of selection is from 0 to 100." +
                            " Good Luck!");

                    builder.setPositiveButton("OK", (dialogInterface, i) -> {
                        name_1 = p1.getText().toString();
                        name_2 = p2.getText().toString();


                        refLb.child(name_1).setValue(0);

                        refLb.child(name_2).setValue(0);


                        Intent gameStart = new Intent(getApplicationContext(), Game.class);

                        gameStart.putExtra("name1", name_1);
                        gameStart.putExtra("name2", name_2);
                        gameStart.putExtra("difficulty", difficulty_index);
                        gameStart.putExtra("user", user_choise);
                        startActivity(gameStart);
                    });
                    builder.show();
                    break;

                case 2:
                    //Hard: 60 seconds, 12 points for each second left. Number selection between 0-150
                    //3 seconds wait time between each guess.

                    builder.setTitle("RULES");
                    builder.setMessage("Rules are simple, the player that you have chosen goes first" +
                            " Your goal is to guess the number correctly" +
                            " You have selected Hard mode, so here are the rules for it: " +
                            "You will have 60 seconds to guess the number correctly. For each second left you will get" +
                            " 12 points. The range of selection is from 0 to 150." +
                            " Good Luck!");

                    builder.setPositiveButton("OK", (dialogInterface, i) -> {
                        name_1 = p1.getText().toString();
                        name_2 = p2.getText().toString();


                        refLb.child(name_1).setValue(0);


                        refLb.child(name_2).setValue(0);


                        Intent gameStart = new Intent(getApplicationContext(), Game.class);

                        gameStart.putExtra("name1", name_1);
                        gameStart.putExtra("name2", name_2);
                        gameStart.putExtra("difficulty", difficulty_index);
                        gameStart.putExtra("user", user_choise);
                        startActivity(gameStart);
                    });
                    builder.show();
                    break;

            }

        });
    }

    class DifficultySpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            //get information from difficulty selection menu
            difficulty_index = i;
            user_difficulty = (String) difficulty.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    class PlayerSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            // we get the information from the 2nd selection (which player goes first)
            user_choise = (String) choise.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}