package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayPaymentInfo1 extends AppCompatActivity {

    Button payOnline;
    TextView ownerName,date,address,totalPrice,Receiver;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment_info1);

        payOnline=findViewById(R.id.paycash1);
        ownerName=findViewById(R.id.ownername2);
        date=findViewById(R.id.date);
        address=findViewById(R.id.address);
        totalPrice=findViewById(R.id.travelfair);
        Receiver=findViewById(R.id.totalprice);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        final String Receiver1=getIntent().getStringExtra("Receiver");
        final String Amount=getIntent().getStringExtra("Amount");
        final String UpiId=getIntent().getStringExtra("UpiId");
        final String UpiName=getIntent().getStringExtra("UpiName");

        ownerName.setText(Receiver1);
        Receiver.setText(Receiver1);
        totalPrice.setText(Amount);

        payOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DisplayPaymentInfo1.this,PaymentPage.class);
                i.putExtra("Amount",Amount);
                i.putExtra("UpiId",UpiId);
                i.putExtra("UpiName",UpiName);
                startActivity(i);
                finish();
            }
        });
    }
}