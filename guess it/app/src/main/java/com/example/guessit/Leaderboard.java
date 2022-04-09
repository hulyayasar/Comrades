package com.example.guessit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {


    DatabaseReference databaseReference;    //initialize database object

    TextView n1, n2, n3, n4, n5, n6, n7, n8, p1, p2, p3, p4, p5, p6, p7, p8;
    ArrayList<String> points = new ArrayList<String>();


    //Button that leads to main page
    public void backtoHomeButton (View view){
        Intent backHome = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backHome);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


        n1 = (TextView) findViewById(R.id.n1);
        n2 = (TextView) findViewById(R.id.n2);
        n3 = (TextView) findViewById(R.id.n3);
        n4 = (TextView) findViewById(R.id.n4);
        n5 = (TextView) findViewById(R.id.n5);
        n6 = (TextView) findViewById(R.id.n6);
        n7 = (TextView) findViewById(R.id.n7);
        n8 = (TextView) findViewById(R.id.n8);

        p1 = (TextView)findViewById(R.id.p1);
        p2 = (TextView)findViewById(R.id.p2);
        p3 = (TextView)findViewById(R.id.p3);
        p4 = (TextView)findViewById(R.id.p4);
        p5 = (TextView)findViewById(R.id.p5);
        p6 = (TextView)findViewById(R.id.p6);
        p7 = (TextView)findViewById(R.id.p7);
        p8 = (TextView)findViewById(R.id.p8);

        getData();
    }


    private void getData() {

        //get the database named leaderboard
        databaseReference = FirebaseDatabase.getInstance().getReference("leaderboard");

        //get the points of players from least to most -max 8
        databaseReference.orderByValue().limitToLast(8).addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                //add names and values to arraylist
                for(int a = 0; a<8; a++){
                    if(points.contains(snapshot.getValue().toString())){
                        break;
                    }else{
                        points.add(snapshot.getValue().toString());
                        points.add(snapshot.getKey());
                    }
                }

                //since points were ordered from least to greatest, we start by putting them to the
                //bottom and go up.
                for(int a = 0; a<(points.size()/2); a++){
                    if(n8.getText().equals("TextView")){
                        n8.setText(points.get(points.size()-1));
                        p8.setText(points.get(points.size()-2));
                        break;
                    }
                    if(n7.getText().equals("TextView")){
                        n7.setText(snapshot.getKey());
                        p7.setText(snapshot.getValue().toString());
                        break;
                    }
                    if(n6.getText().equals("TextView")){
                        n6.setText(snapshot.getKey());
                        p6.setText(snapshot.getValue().toString());
                        break;
                    }
                    if(n5.getText().equals("TextView")){
                        n5.setText(snapshot.getKey());
                        p5.setText(snapshot.getValue().toString());
                        break;
                    }
                    if(n4.getText().equals("TextView")){
                        n4.setText(snapshot.getKey());
                        p4.setText(snapshot.getValue().toString());
                        break;
                    }
                    if(n3.getText().equals("TextView")){
                        n3.setText(snapshot.getKey());
                        p3.setText(snapshot.getValue().toString());
                        break;
                    }
                    if(n2.getText().equals("TextView")){
                        n2.setText(snapshot.getKey());
                        p2.setText(snapshot.getValue().toString());
                        break;
                    }
                    if(n1.getText().equals("TextView")){
                        n1.setText(snapshot.getKey());
                        p1.setText(snapshot.getValue().toString());
                        break;
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }





}