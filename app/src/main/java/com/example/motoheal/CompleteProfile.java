package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteProfile extends AppCompatActivity {

    ImageView imageView;
    TextView profilename,profilephoneno,profileEmail,login12;
    EditText Fname,Lname,Pnumber;

    Uri imagedata;
    private static final int RequestCode=123;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        imageView=findViewById(R.id.imageview);
        profilename=findViewById(R.id.profilename);
        profilephoneno=findViewById(R.id.profilephoneno);
        profileEmail=findViewById(R.id.profileemail);
        Fname=findViewById(R.id.fName);
        Lname=findViewById(R.id.lName);
        Pnumber=findViewById(R.id.Phoneno1);
        login12=findViewById(R.id.login12);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseStorage=FirebaseStorage.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating Your Profile");
        progressDialog.setCancelable(false);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode);

            }
        });

        firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Personal details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstname=snapshot.child("firstName").getValue().toString();
                String lastname=snapshot.child("lastName").getValue().toString();
                String pho=snapshot.child("phoneNumber").getValue().toString();
                String email=snapshot.child("email").getValue().toString();


                profilename.setText(firstname);
                Fname.setText(firstname);
                Lname.setText(lastname);
                Pnumber.setText(pho);
                profileEmail.setText(email);
                profilephoneno.setText(pho);
                //Glide.with(CompleteProfile.this).load(snapshot.child("profilePic").getValue()).placeholder(R.drawable.avatar).into(imageView);

                //Glide.with(CompleteProfile.this).load(snapshot.child("profilePic").getValue()).placeholder(R.drawable.avatar).into(imageView);

                Picasso.get().load((String) snapshot.child("profilePic").getValue()).placeholder(R.drawable.avatar).into(imageView);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        login12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String firstName=Fname.getText().toString();
                final String Lastname=Lname.getText().toString();
                final String Phone=Pnumber.getText().toString();
                final String email=profileEmail.getText().toString();

                progressDialog.show();

                if (imagedata!=null){
                    final StorageReference storageReference=firebaseStorage.getReference().child("ProfilePic").child(firebaseAuth.getUid());
                    storageReference.putFile(imagedata).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String url=uri.toString();
                                        User user=new User(firstName,Lastname,Phone,email,"User",url);
                                        firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Personal details").setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                progressDialog.dismiss();
                                                Intent i=new Intent(CompleteProfile.this,MapActivity.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }
                else{

                    firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Personal details").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String n=snapshot.child("profilePic").getValue().toString();

                            User user=new User(firstName,Lastname,Phone,email,"User",n);
                            firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Personal details").setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressDialog.dismiss();
                                    Intent i=new Intent(CompleteProfile.this,MapActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RequestCode && resultCode==RESULT_OK && data!=null){
            imagedata=data.getData();
            imageView.setImageURI(imagedata);
        }
    }
}