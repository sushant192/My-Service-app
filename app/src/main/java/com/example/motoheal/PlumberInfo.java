package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlumberInfo extends AppCompatActivity {

    TextView garageName,grab,sent,address,noofworker,gphone,available,ratings;
    Dialog dialog;
    ImageView pro;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber_info);

        final String name=getIntent().getStringExtra("name");
        final String owner=getIntent().getStringExtra("owner");

        final String Service=getIntent().getStringExtra("Service");
        final String Issues=getIntent().getStringExtra("Issues");

        garageName=findViewById(R.id.gname);
        garageName.setText(name);
        grab=findViewById(R.id.grab2);
        pro=findViewById(R.id.pro);
        address=findViewById(R.id.address1);
        noofworker=findViewById(R.id.noofworker);
        gphone=findViewById(R.id.gphone);
        available=findViewById(R.id.availableworkers);
        ratings=findViewById(R.id.ratings);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("Partners").child("partners").child(owner).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                float Sum=0;
                String s=snapshot.child("Business Details").child("profilepic").getValue().toString();
                String s1=snapshot.child("Business Details").child("shopAddress").getValue().toString();
                String s2=snapshot.child("Business Details").child("noofworkers").getValue().toString();
                String s3=snapshot.child("Business Details").child("shopNumber").getValue().toString();

                for (DataSnapshot snapshot1:snapshot.child("Ratings").getChildren()){
                    String g=snapshot1.child("stars").getValue().toString();
                    float h=Float.parseFloat(g);
                    Sum=Sum+h;
                }

                float j=Sum/snapshot.child("Ratings").getChildrenCount();
                ratings.setText(String.valueOf(j));

                address.setText(s1);
                long d=snapshot.child("Worker Details").getChildrenCount()+1;
                noofworker.setText(String.valueOf(d));
                available.setText(String.valueOf(d));
                gphone.setText(s3);
                Picasso.get().load(s).placeholder(R.drawable.avatar).into(pro);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.request_sent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        sent=dialog.findViewById(R.id.sent);

        grab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                final String date=simpleDateFormat.format(calendar.getTime());

                    final String k=firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").push().getKey();
                    Request request1=new Request(name,owner,k,"","",date,"","-");
                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Info").setValue(request1);

                    PlumberRequest request=new PlumberRequest(name,owner,k,"","",Service,Issues);
                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Details").setValue(request);

                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Personal details").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String name=snapshot.child("firstName").getValue().toString();
                            String number=snapshot.child("phoneNumber").getValue().toString();
                            String pic=snapshot.child("profilePic").getValue().toString();

                            User1 u1=new User1(name,"",number,"","",auth.getUid(),k,pic,"Plumber1",date,"","-");
                            firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Info").setValue(u1);

                            PlumberPartnerRequest u=new PlumberPartnerRequest(name,"",number,"","",auth.getUid(),k,Service,Issues);
                            firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Details").setValue(u);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    dialog.show();

            }
        });


        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlumberInfo.this,MapActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });

    }
}