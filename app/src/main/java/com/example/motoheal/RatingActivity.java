package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView submit;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar=findViewById(R.id.ratings);
        submit=findViewById(R.id.submit);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        final String OwnerId=getIntent().getStringExtra("ownerid");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=String.valueOf(ratingBar.getRating());

                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                final String date=simpleDateFormat.format(calendar.getTime());

                Ratings rating=new Ratings(s,date,auth.getUid(),OwnerId);
                firebaseDatabase.getReference().child("Partners").child("partners").child(OwnerId).child("Ratings").push().setValue(rating);

                Intent i=new Intent(RatingActivity.this,MapActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}