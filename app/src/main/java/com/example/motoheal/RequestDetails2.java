package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RequestDetails2 extends AppCompatActivity {

    ImageView imageView,image1,image2,image3;
    Button accept,callnow;
    TextView custname,date,custnumber,totalrooms,workdays,totalamount,gst,travelfair,total;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details2);

        imageView=findViewById(R.id.profilepic);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        accept=findViewById(R.id.paycash);
        custname=findViewById(R.id.ownername1);
        date=findViewById(R.id.date);
        custnumber=findViewById(R.id.number1);
        totalrooms=findViewById(R.id.totalrooms);
        workdays=findViewById(R.id.workdays);
        totalamount=findViewById(R.id.totalamount);
        gst=findViewById(R.id.gst);
        travelfair=findViewById(R.id.travelfair);
        total=findViewById(R.id.total);
        callnow=findViewById(R.id.callnow);

        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        String name =getIntent().getStringExtra("name");
        String phone =getIntent().getStringExtra("phone");
        String pic =getIntent().getStringExtra("pic");
        final String id =getIntent().getStringExtra("id");
        final String requestId=getIntent().getStringExtra("requestId");
        final String Date=getIntent().getStringExtra("date");

        custname.setText(name);
        custnumber.setText(phone);
        date.setText(Date);
        Picasso.get().load(pic).placeholder(R.drawable.avatar).into(imageView);

        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        firebaseDatabase.getReference().child("Users").child(id).child("Requests").child(requestId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Howmanydays=snapshot.child("Details").child("howmanyDays").getValue().toString();
                workdays.setText(Howmanydays);

                String Totalrooms=snapshot.child("Details").child("totalRooms").getValue().toString();
                totalrooms.setText(Totalrooms);

                if (snapshot.hasChild("Images")){
                    if (snapshot.child("Images").hasChild("RoomImage1")){
                        String room1=snapshot.child("Images").child("RoomImage1").getValue().toString();
                        Picasso.get().load(room1).placeholder(R.drawable.ic_baseline_image_24).into(image1);
                    }
                    if (snapshot.child("Images").hasChild("RoomImage2")){
                        String room2=snapshot.child("Images").child("RoomImage2").getValue().toString();
                        Picasso.get().load(room2).placeholder(R.drawable.ic_baseline_image_24).into(image2);
                    }
                    if (snapshot.child("Images").hasChild("RoomImage3")){
                        String room3=snapshot.child("Images").child("RoomImage3").getValue().toString();
                        Picasso.get().load(room3).placeholder(R.drawable.ic_baseline_image_24).into(image3);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReference().child("Users").child(id).child("Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    String s=snapshot1.child("Info").child("requestId").getValue().toString();
                    //String s1=snapshot1.child("custId").getValue().toString();

                    if (snapshot1.getKey().contentEquals(requestId)  && snapshot1.child("Info").child("flag").getValue().toString().contentEquals("1")){

                        accept.setVisibility(View.INVISIBLE);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReference().child("Prices").child("Plumber Fitting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s=snapshot.getValue().toString();
                String s1=workdays.getText().toString();

                int s2=Integer.parseInt(s)*Integer.parseInt(s1);
                totalamount.setText(String.valueOf(s2));

                int h= (int) (s2*0.18);

                gst.setText(String.valueOf(h));
                total.setText(String.valueOf(s2+h));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase.getReference().child("Requests").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            String s=snapshot1.child("Info").child("flag").getValue().toString();
                            String s1=snapshot1.child("Info").child("custId").getValue().toString();
                            String s2=snapshot1.getKey();

                            if (s.contentEquals("")&& s1.contentEquals(id) && s2.contentEquals(requestId)){
                                //firebaseDatabase.getReference().child("Requests").child(firebaseAuth.getUid()).child(snapshot1.getKey()).child("Info").child("flag").setValue("1");
                                firebaseDatabase.getReference().child("Requests").child(firebaseAuth.getUid()).child(snapshot1.getKey()).child("Info").child("totalAmount").setValue(totalamount.getText().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                firebaseDatabase.getReference().child("Users").child(id).child("Requests").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            String s=snapshot1.child("Info").child("requestId").getValue().toString();
                            //String s1=snapshot1.child("custId").getValue().toString();

                            if (snapshot1.getKey().contentEquals(requestId)){
                                firebaseDatabase.getReference().child("Users").child(id).child("Requests").child(snapshot1.getKey()).child("Info").child("flag").setValue("1");
                                firebaseDatabase.getReference().child("Users").child(id).child("Requests").child(snapshot1.getKey()).child("Info").child("totalAmount").setValue(totalamount.getText().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                accept.setVisibility(View.INVISIBLE);

                Intent i=new Intent(RequestDetails2.this,PartnerMapActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void makePhoneCall() {
        String number = custnumber.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(RequestDetails2.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(RequestDetails2.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(RequestDetails2.this, "Failed", Toast.LENGTH_SHORT).show();
        }
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