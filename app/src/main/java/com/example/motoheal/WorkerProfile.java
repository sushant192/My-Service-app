package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class WorkerProfile extends AppCompatActivity {

    TextView login12;
    EditText Fname,dob,Pnumber,address;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        Fname=findViewById(R.id.fName);
        dob=findViewById(R.id.lName);
        login12=findViewById(R.id.login12);
        address=findViewById(R.id.profileemail);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseStorage=FirebaseStorage.getInstance();

        String Address=getIntent().getStringExtra("address");
        String DOB=getIntent().getStringExtra("dob");
        String FullName=getIntent().getStringExtra("fullname");
        String Phonenumber=getIntent().getStringExtra("phonenumber");
        final String Id=getIntent().getStringExtra("id");

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating Your Profile");
        progressDialog.setCancelable(false);

        Fname.setText(FullName);
        dob.setText(DOB);
        address.setText(Address);

        final String s=Fname.getText().toString();

        login12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String firstName=Fname.getText().toString();
                final String Dob=dob.getText().toString();
                final String Address1=address.getText().toString();
                progressDialog.show();


                firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Worker Details").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            if (snapshot1.child("workername").getValue().toString().contentEquals(s)){



                                Worker worker=new Worker(firstName,"",Id,Address1,Dob,"Male",snapshot1.child("image1").getValue().toString(),snapshot1.child("image2").getValue().toString());

                                //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Worker Details").child(snapshot1.getKey()).child("workername").setValue(firstName);
                                //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Worker Details").child(snapshot1.getKey()).child("workerdob").setValue(Dob);
                                //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Worker Details").child(snapshot1.getKey()).child("workeraddress").setValue(Address1);

                                firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Worker Details").child(snapshot1.getKey()).setValue(worker).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        progressDialog.dismiss();

                                        Intent i=new Intent(WorkerProfile.this,CoWorkers.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}