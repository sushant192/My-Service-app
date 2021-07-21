package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayPaymentInfo2 extends AppCompatActivity {

    Button payCash,payOnline;
    TextView ownerName,date,address,timeTaken,basePrice,travelFair,totalPrice,gst;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment_info2);

        payCash=findViewById(R.id.paycash);
        payOnline=findViewById(R.id.payonline);
        ownerName=findViewById(R.id.ownername1);
        date=findViewById(R.id.date);
        address=findViewById(R.id.address);
        timeTaken=findViewById(R.id.timetaken);
        basePrice=findViewById(R.id.baseprice);
        travelFair=findViewById(R.id.travelfair);
        totalPrice=findViewById(R.id.totalprice);
        gst=findViewById(R.id.gst);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        String ownerName1=getIntent().getStringExtra("ownername");
        final String ownerId=getIntent().getStringExtra("ownerId");
        final String totalamount=getIntent().getStringExtra("totalamount");
        final String timetaken=getIntent().getStringExtra("timetaken");
        final String Date=getIntent().getStringExtra("date");
        final String m1=getIntent().getStringExtra("n");
        final String n1=getIntent().getStringExtra("n1");

        ownerName.setText(ownerName1);
        date.setText(Date);
    }
}