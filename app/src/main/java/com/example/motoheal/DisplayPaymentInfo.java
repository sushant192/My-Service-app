package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DisplayPaymentInfo extends AppCompatActivity {

    Button payCash,payOnline,callnow;
    TextView ownerName,date,address,timeTaken,basePrice,travelFair,totalPrice,gst,submit;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;


    private static String ownerId;

    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment_info);

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
        callnow=findViewById(R.id.call);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();


        String ownerName1=getIntent().getStringExtra("ownername");
        ownerId=getIntent().getStringExtra("ownerId");
        final String totalamount=getIntent().getStringExtra("totalamount");
        final String timetaken=getIntent().getStringExtra("timetaken");
        final String Date=getIntent().getStringExtra("date");
        final String m1=getIntent().getStringExtra("n");
        final String n1=getIntent().getStringExtra("n1");

        ownerName.setText(ownerName1);
        date.setText(Date);

        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makePhoneCall();

            }
        });



        firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Business Details").child("shopAddress").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                address.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        timeTaken.setText(timetaken);

        basePrice.setText(totalamount);

        int s1=Integer.parseInt(totalamount);
        int s= (int) (s1*0.18);
        gst.setText(String.valueOf(s));

        int l=Integer.parseInt(basePrice.getText().toString());
        final int m=Integer.parseInt(gst.getText().toString());
        final int n=l+m;
        totalPrice.setText(String.valueOf(n));



        payCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Baseprice=basePrice.getText().toString();
                String TravelFair=travelFair.getText().toString();
                String TotalPrice=totalPrice.getText().toString();

                //dialog.show();

                firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            String s=snapshot1.child("Info").child("flag").getValue().toString();
                            String s1=snapshot1.getKey();
                            String s2=snapshot1.child("Info").child("clickflag").getValue().toString();
                            String s3=snapshot1.child("Info").child("businessName").getValue().toString();

                            if (s.contentEquals("1") && s1.contentEquals(m1) && s2.contentEquals("") && s3.contentEquals(n1)){
                                firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(snapshot1.getKey()).child("Info").child("clickflag").setValue("1");
                                firebaseDatabase.getReference().child("Requests").child(ownerId).child(snapshot1.getKey()).child("Info").child("flag").setValue("1");
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });



                String key=firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Payments").child("Cash").push().getKey();

                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                final String date=simpleDateFormat.format(calendar.getTime());

                Transaction transaction=new Transaction("Cash",Baseprice,TravelFair,TotalPrice,ownerId,key,date);
                firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Payments").child("Cash").child(key).setValue(transaction);
                firebaseDatabase.getReference().child("Admin").child("Transaction Details").child("Cash").child(key).setValue(transaction);

                Transaction1 transaction1=new Transaction1("Cash",Baseprice,TravelFair,TotalPrice,auth.getUid(),key,date);
                firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Transaction Details").child("Cash").child(key).setValue(transaction1);

                Notification1 notification1=new Notification1("Cash Payments","Cash Payment received",auth.getUid(),"",date,"",key);
                firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Notifications").child("Cash").child(key).setValue(notification1);

                Transaction1 transaction2=new Transaction1("Cash",Baseprice,TravelFair,TotalPrice,auth.getUid(),key,date);
                firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Balances Due").child(key).setValue(transaction2).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(DisplayPaymentInfo.this, "Payment Successful", Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(DisplayPaymentInfo.this,RatingActivity.class);
                        i.putExtra("ownerid",ownerId);
                        startActivity(i);
                        finish();
                    }
                });




            }
        });

        payOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DisplayPaymentInfo.this,PaymentPage.class);
                i.putExtra("baseprice",basePrice.getText().toString());
                i.putExtra("travelfair",travelFair.getText().toString());
                i.putExtra("totalprice",totalPrice.getText().toString());
                i.putExtra("ownerid",ownerId);
                i.putExtra("m1",m1);
                i.putExtra("n1",n1);
                startActivity(i);
                finish();
            }
        });
    }

    private void makePhoneCall() {
        //String number = custnumber.getText().toString();

        firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Personal Details").child("phoneNumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String number=snapshot.getValue().toString();

                if (number.trim().length() > 0) {
                    if (ContextCompat.checkSelfPermission(DisplayPaymentInfo.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DisplayPaymentInfo.this,
                                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    } else {
                        String dial = "tel:" + number;
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                    }
                } else {
                    Toast.makeText(DisplayPaymentInfo.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}