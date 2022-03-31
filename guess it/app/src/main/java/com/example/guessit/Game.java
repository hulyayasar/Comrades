package com.example.guessit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Game extends AppCompatActivity{

    String name1, name2, id1, id2, difficulty_selection, turn, user_guess, counterValue, currentUser;
    int difficulty_selection_int, to_be_guessed;
    TextView counter, difficulty_show, user_turn, number_to_guess, hint;
    Button check, start_game, nextPlayer, lbButton, homeButton;
    Random rd = new Random();
    Boolean won = false;
    EditText guess;
    int termination = 0;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refLb = database.getReference("leaderboard");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        counter = (TextView) findViewById(R.id.counter);
        difficulty_show = (TextView) findViewById(R.id.difficulty_show);
        user_turn = (TextView) findViewById(R.id.user_turn);
        check = (Button) findViewById(R.id.check_button);
        start_game = (Button) findViewById(R.id.start_game);
        number_to_guess = (TextView) findViewById(R.id.number_to_guess);
        guess = (EditText) findViewById(R.id.user_guess);
        hint = (TextView) findViewById(R.id.hint_view);
        nextPlayer = (Button) findViewById(R.id.nextPlayer);
        homeButton = (Button) findViewById(R.id.toHome);
        lbButton = (Button) findViewById(R.id.buttonLB2);

        homeButton.setVisibility(View.INVISIBLE);
        lbButton.setVisibility(View.INVISIBLE);
        nextPlayer.setVisibility(View.INVISIBLE);
        check.setVisibility(View.INVISIBLE);

        //getting difficulty selection from game selection class
        difficulty_selection = getIntent().getExtras().get("difficulty").toString();
        difficulty_selection_int = Integer.parseInt(difficulty_selection);

        //names will be used to display it on the gamescreen
        name1 = getIntent().getExtras().get("name1").toString();
        name2 = getIntent().getExtras().get("name2").toString();



        //getting who starts first
        turn = getIntent().getExtras().get("user").toString();

        if(difficulty_selection_int == 0){
            difficulty_show.setText("Difficulty: Easy");
        }
        else if(difficulty_selection_int == 1){
            difficulty_show.setText("Difficulty: Medium");

        }
        else if(difficulty_selection_int == 2){
            difficulty_show.setText("Difficulty: Hard");

        }



        //setting the first choice of the players
        if (turn.equals("Player 1")){
            user_turn.setText("Players Turn: "+name1);
            currentUser = name1;
        } else{
            user_turn.setText("Players Turn: "+name2);
            currentUser = name2;
        }



    }

    public void start_game(View view){
        int easy = 50;
        int normal = 100;
        int hard = 150;

        check.setVisibility(View.VISIBLE);
        if(difficulty_selection_int == 0){
           to_be_guessed= rd.nextInt(easy);
           System.out.println("Number is: "+to_be_guessed);
           if(to_be_guessed>=10){
               number_to_guess.setText("**");
           }else{
               number_to_guess.setText("*");
           }
           start_game.setVisibility(View.INVISIBLE);
           easy_mode();
        }
        else if(difficulty_selection_int == 1){
            to_be_guessed = rd.nextInt(normal);
            System.out.println("Number is: "+to_be_guessed);
            if (to_be_guessed>=100){
                number_to_guess.setText("***");
            }
            else if (to_be_guessed>=10){
                number_to_guess.setText("**");
            } else{
                number_to_guess.setText("*");
            }
            start_game.setVisibility(View.INVISIBLE);
            normal_mode();
        }
        else if(difficulty_selection_int == 2){
            to_be_guessed = rd.nextInt(hard);
            System.out.println("Number is: "+to_be_guessed);
            if (to_be_guessed>=100){
                number_to_guess.setText("***");
            }
            else if (to_be_guessed>=10){
                number_to_guess.setText("**");
            } else{
                number_to_guess.setText("*");
            }
            start_game.setVisibility(View.INVISIBLE);
            hard_mode();
        }

    }


    public void check_guess(View view){

        user_guess = guess.getText().toString();

        if (to_be_guessed == Integer.parseInt(user_guess)){
            counterValue = counter.getText().toString();
            won = true;
            number_to_guess.setText("Correct");
            start_game.setVisibility(View.VISIBLE);
            nextPlayer.setVisibility(View.VISIBLE);
            counter.setText("0");
            termination++;
            start_game.setVisibility(View.INVISIBLE);
            check.setVisibility(View.INVISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
            int points = Integer.parseInt(counterValue)-1;

            if (difficulty_selection_int == 0){
                int easy_points = (points)*3;
                builder.setTitle("Congratulations");

                builder.setMessage("In Easy mode " + currentUser + " you have made " + easy_points + " points in total.");
                builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    refLb.child(currentUser).setValue(easy_points);
                });
                builder.show();
            }
            if (difficulty_selection_int == 1){
                int easy_points = (points)*6;
                builder.setTitle("Congratulations");

                builder.setMessage("In Normal mode " + currentUser + " you have made " + easy_points + " points in total.");
                builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    refLb.child(currentUser).setValue(easy_points);
                });
                builder.show();
            }

            if (difficulty_selection_int == 2){
                int easy_points = (points)*12;
                builder.setTitle("Congratulations");

                builder.setMessage("In Hard mode " + currentUser + " you have made " + easy_points + " points in total.");
                builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    refLb.child(currentUser).setValue(easy_points);
                });
                builder.show();
            }

            if(termination == 2){
                homeButton.setVisibility(View.VISIBLE);
                lbButton.setVisibility(View.VISIBLE);
                start_game.setVisibility(View.INVISIBLE);
                check.setVisibility(View.INVISIBLE);
                nextPlayer.setVisibility(View.INVISIBLE);
            }

        }

        else if (Integer.parseInt(user_guess) < to_be_guessed){
            hint.setText("Guess is low");
        }
        else if (Integer.parseInt(user_guess) > to_be_guessed){
            hint.setText("Guess is high");
        }

    }



    public void next_player (View view){
        start_game.setVisibility(View.VISIBLE);
        counter.setText("0");
        number_to_guess.setText("Number to guess");
        nextPlayer.setVisibility(View.INVISIBLE);
        won = false;
        turn = getIntent().getExtras().get("user").toString();
        if (turn.equals("Player 1")){
            user_turn.setText("Players Turn: "+name2);
            currentUser = name2;

        } else{
            user_turn.setText("Players Turn: "+name1);
            currentUser = name1;
        }

        if(termination == 2){
            homeButton.setVisibility(View.VISIBLE);
            lbButton.setVisibility(View.VISIBLE);
            start_game.setVisibility(View.INVISIBLE);
            check.setVisibility(View.INVISIBLE);
            nextPlayer.setVisibility(View.INVISIBLE);
        }



    }



    public void toHome (View view){
        Intent backHome = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backHome);
    }

    public void toLB(View view){
        Intent leaderboardIntent = new Intent(getApplicationContext(), Leaderboard.class);
        startActivity(leaderboardIntent);
    }


    public void easy_mode(){
            new CountDownTimer(100000, 1000) {
                public void onTick(long millisUntilFinished) {
                    counter.setText(String.valueOf(millisUntilFinished / 1000));
                    if(won == true){
                        cancel();
                    }
                }
                public void onFinish() {
                    termination++;
                    counter.setText("done!");
                    if(termination == 2){
                        homeButton.setVisibility(View.VISIBLE);
                        lbButton.setVisibility(View.VISIBLE);
                        start_game.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.INVISIBLE);
                        nextPlayer.setVisibility(View.INVISIBLE);
                    } else{
                        start_game.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.INVISIBLE);
                        nextPlayer.setVisibility(View.VISIBLE);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setTitle("Sorry but your time is up");

                    builder.setMessage("In Easy mode " + currentUser + " you have made 0 points in total.");
                    builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    });
                    builder.show();
                }
            }.start();
    }


    public void normal_mode(){

            new CountDownTimer(80000, 1000) {
                public void onTick(long millisUntilFinished) {
                    counter.setText(String.valueOf(millisUntilFinished / 1000));
                    if(won == true){
                        cancel();
                    }
                }
                public void onFinish() {
                    termination++;
                    counter.setText("done!");
                    if(termination == 2){
                        homeButton.setVisibility(View.VISIBLE);
                        lbButton.setVisibility(View.VISIBLE);
                        start_game.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.INVISIBLE);
                        nextPlayer.setVisibility(View.INVISIBLE);
                    } else{
                        start_game.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.INVISIBLE);
                        nextPlayer.setVisibility(View.VISIBLE);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setTitle("Sorry but your time is up");

                    builder.setMessage("In Easy mode " + currentUser + " you have made 0 points in total.");
                    builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    });
                    builder.show();

                }
            }.start();
    }

    public void hard_mode(){

            new CountDownTimer(60000, 1000) {
                public void onTick(long millisUntilFinished) {
                    counter.setText(String.valueOf(millisUntilFinished / 1000));
                    if(won == true){
                        cancel();
                    }
                }
                public void onFinish() {
                    termination++;
                    counter.setText("done!");
                    if(termination == 2){
                        homeButton.setVisibility(View.VISIBLE);
                        lbButton.setVisibility(View.VISIBLE);
                        start_game.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.INVISIBLE);
                        nextPlayer.setVisibility(View.INVISIBLE);
                    } else{
                        start_game.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.INVISIBLE);
                        nextPlayer.setVisibility(View.VISIBLE);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setTitle("Sorry but your time is up");

                    builder.setMessage("In Easy mode " + currentUser + " you have made 0 points in total.");
                    builder.setPositiveButton("OK", (dialogInterface, i) -> {
                    });
                    builder.show();

                }
            }.start();
        }

}
