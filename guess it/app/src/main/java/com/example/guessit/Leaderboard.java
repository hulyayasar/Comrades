package com.example.guessit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class Leaderboard extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    TextView first_placeName = (TextView) findViewById(R.id.firstPlace_Name);
    TextView first_placePoints = (TextView)findViewById(R.id.firstPlace_Points);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


        getData();
    }

    private void getData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("usernames");

        databaseReference.child("user3").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();

                String username = String.valueOf(dataSnapshot.child("name").getValue());
                String points = String.valueOf(dataSnapshot.child("points").getValue());

                first_placeName.setText(username);
                first_placePoints.setText(points);
            }
        });

    }


}