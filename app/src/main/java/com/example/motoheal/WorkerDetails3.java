package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class WorkerDetails3 extends AppCompatActivity {

    TextView fullname,workerid,dob,address,phonenumber;
    Button okay;

    FirebaseAuth auth;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;

    ImageView image1,image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details3);

        fullname=findViewById(R.id.fullname);
        dob=findViewById(R.id.dob);
        address=findViewById(R.id.address);
        workerid=findViewById(R.id.workerid);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);


        auth=FirebaseAuth.getInstance();
        user1=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        String Address=getIntent().getStringExtra("address");
        String DOB=getIntent().getStringExtra("dob");
        String FullName=getIntent().getStringExtra("fullname");
        String Phonenumber=getIntent().getStringExtra("phonenumber");
        String Id=getIntent().getStringExtra("id");
        String Image1=getIntent().getStringExtra("image1");
        String Image2=getIntent().getStringExtra("image2");

        fullname.setText(FullName);
        workerid.setText(Id);
        dob.setText(DOB);
        address.setText(Address);

        Picasso.get().load(Image1).placeholder(R.drawable.avatar).into(image1);
        Picasso.get().load(Image2).placeholder(R.drawable.avatar).into(image2);
    }
}