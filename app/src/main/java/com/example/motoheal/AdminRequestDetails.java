package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class AdminRequestDetails extends AppCompatActivity {

    TextView Date,FullName,PhoneNumber,Email,Address,DOB,PartnerType,IdProof,ShopName,ShopNumber,ShopAddress,NoOfWorkers,WorkshopName,GstNumber,WorkerName,WorkerAddress,WorkerDob,WorkerIdProof;
    ImageView image1,image2,image3,image4;
    Button Accept;

    FirebaseAuth auth;
    ProgressDialog loader;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_details);

        Date=findViewById(R.id.date);
        FullName=findViewById(R.id.fullname);
        PhoneNumber=findViewById(R.id.phonenumber);
        Email=findViewById(R.id.email);
        Address=findViewById(R.id.address);
        DOB=findViewById(R.id.dob);
        PartnerType=findViewById(R.id.partnertype);
        IdProof=findViewById(R.id.idproof);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        ShopName=findViewById(R.id.shopname);
        ShopNumber=findViewById(R.id.shopnumber);
        ShopAddress=findViewById(R.id.shopaddress);
        NoOfWorkers=findViewById(R.id.noofworkers);
        WorkshopName=findViewById(R.id.workshopname);
        GstNumber=findViewById(R.id.gstno);
        WorkerName=findViewById(R.id.workername);
        WorkerAddress=findViewById(R.id.workeraddress);
        WorkerDob=findViewById(R.id.workerdob);
        WorkerIdProof=findViewById(R.id.workeridproof);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);

        Accept=findViewById(R.id.paycash);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        loader = new ProgressDialog(this);

        String Date1 =getIntent().getStringExtra("date");
        Date.setText(Date1);
        final String Fullname1 =getIntent().getStringExtra("fullname");
        FullName.setText(Fullname1);
        final String Phonenumber1 =getIntent().getStringExtra("phonenumber");
        PhoneNumber.setText(Phonenumber1);
        final String Email1 =getIntent().getStringExtra("email");
        Email.setText(Email1);
        final String Address1=getIntent().getStringExtra("address");
        Address.setText(Address1);
        final String Dob1=getIntent().getStringExtra("dob");
        DOB.setText(Dob1);
        final String Partnertype1=getIntent().getStringExtra("partnertype");
        PartnerType.setText(Partnertype1);
        final String Id1 =getIntent().getStringExtra("id");
        IdProof.setText(Id1);
        final String Image11=getIntent().getStringExtra("image1");
        Picasso.get().load(Image11).placeholder(R.drawable.ic_baseline_image_24).into(image1);
        final String Image22=getIntent().getStringExtra("image2");
        Picasso.get().load(Image22).placeholder(R.drawable.ic_baseline_image_24).into(image2);


        final String Shopname1=getIntent().getStringExtra("shopname");
        ShopName.setText(Shopname1);
        final String Shopnumber1=getIntent().getStringExtra("shopnumber");
        ShopNumber.setText(Shopnumber1);
        final String Shopaddress1=getIntent().getStringExtra("shopaddress");
        ShopAddress.setText(Shopaddress1);
        final String Noofworkers1=getIntent().getStringExtra("noofworkers");
        NoOfWorkers.setText(Noofworkers1);
        final String Workshopname1=getIntent().getStringExtra("workshopname");
        WorkshopName.setText(Workshopname1);
        final String Gst1=getIntent().getStringExtra("gst");
        GstNumber.setText(Gst1);


        final String Workername1=getIntent().getStringExtra("workername");
        WorkerName.setText(Workername1);
        final String Workeraddress1=getIntent().getStringExtra("workeraddress");
        WorkerAddress.setText(Workeraddress1);
        final String Workerdob1=getIntent().getStringExtra("workerdob");
        WorkerDob.setText(Workerdob1);
        final String Workerid1=getIntent().getStringExtra("workerid");
        WorkerIdProof.setText(Workerid1);
        final String Workergender1=getIntent().getStringExtra("workergender");
        final String Workeridpic11=getIntent().getStringExtra("workeridpic1");
        Picasso.get().load(Workeridpic11).placeholder(R.drawable.ic_baseline_image_24).into(image3);
        final String Workeridpic22=getIntent().getStringExtra("workeridpic2");
        Picasso.get().load(Workeridpic22).placeholder(R.drawable.ic_baseline_image_24).into(image4);

        final String Password1=getIntent().getStringExtra("password");
        final String pendingId=getIntent().getStringExtra("pendingid");


        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loader.show();

                auth.createUserWithEmailAndPassword(Email1,Password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String RegisteredUserID = currentUser.getUid();

                            if (Partnertype1.contentEquals("Garage Owner")){
                                Business business=new Business(Shopname1,Shopnumber1,"",Shopaddress1,Noofworkers1,Workshopname1,Gst1,RegisteredUserID);
                                firebaseDatabase.getReference().child("Partners").child("Business").child("Garage Services").push().setValue(business);
                            }
                            if (Partnertype1.contentEquals("Plumber")){
                                Business business1=new Business(Shopname1,Shopnumber1,"",Shopaddress1,Noofworkers1,Workshopname1,Gst1,RegisteredUserID);
                                firebaseDatabase.getReference().child("Partners").child("Business").child("Plumbing Business").push().setValue(business1);
                            }
                            if (Partnertype1.contentEquals("Electrician")){
                                Business business2=new Business(Shopname1,Shopnumber1,"",Shopaddress1,Noofworkers1,Workshopname1,Gst1,RegisteredUserID);
                                firebaseDatabase.getReference().child("Partners").child("Business").child("Electrician business").push().setValue(business2);
                            }
                            if (Partnertype1.contentEquals("Painter")){
                                Business business3=new Business(Shopname1,Shopnumber1,"",Shopaddress1,Noofworkers1,Workshopname1,Gst1,RegisteredUserID);
                                firebaseDatabase.getReference().child("Partners").child("Business").child("Painting business").push().setValue(business3);
                            }

                            Partner partner=new Partner(Fullname1,Phonenumber1,Email1,Password1,"Partner",Address1,Dob1,Partnertype1,Id1,Image11,Image22);
                            firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Personal Details").setValue(partner);

                            Service service=new Service(Shopname1,Shopnumber1,"",Shopaddress1,Noofworkers1,"No Image",Workshopname1,Gst1);
                            firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Business Details").setValue(service);

                            Worker worker=new Worker(Workername1,"",Workerid1,Workeraddress1,Workerdob1,Workergender1,Workeridpic11,Workeridpic22);
                            firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Worker Details").push().setValue(worker);

                            Worker1 worker1=new Worker1(Workername1,"",Workerid1,Workeraddress1,Workerdob1,Workergender1,RegisteredUserID);
                            firebaseDatabase.getReference().child("Partners").child("Workers").push().setValue(worker1);

                            firebaseDatabase.getReference().child("Pending Requests").child(pendingId).child("status").setValue("1");

                            Toast.makeText(AdminRequestDetails.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(AdminRequestDetails.this, AdminHome.class);
                            //i.putExtra("phone",number);
                            startActivity(i);
                            finish();
                            loader.dismiss();
                        }
                        else{
                            Toast.makeText(AdminRequestDetails.this,"Registration failed",Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                        }
                    }
                });

            }
        });















    }
}