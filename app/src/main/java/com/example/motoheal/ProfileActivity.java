package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    EditText fname,lname,mobileNumber,email,password;
    TextView register;

    CheckBox terms;

    FirebaseAuth auth;
    ProgressDialog loader;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        mobileNumber=findViewById(R.id.mobile);
        email=findViewById(R.id.email1);
        password=findViewById(R.id.password1);
        register=findViewById(R.id.register);
        terms=findViewById(R.id.terms);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        loader=new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Email=email.getText().toString();
                String Password=password.getText().toString();
                final String number=mobileNumber.getText().toString();
                final String Fname=fname.getText().toString();
                final String Lname=lname.getText().toString();


                if(TextUtils.isEmpty(Email)){
                    email.setError("email is required");
                    return;
                }
                if(TextUtils.isEmpty(Fname)){
                    fname.setError("email is required");
                    return;
                }
                if(TextUtils.isEmpty(Lname)){
                    lname.setError("email is required");
                    return;
                }
                if (!terms.isChecked()){
                    terms.setError("Required");
                    return;
                }
                if(TextUtils.isEmpty(number)){
                    mobileNumber.setError("Phone number is required");
                    return;
                }
                if (Password.length()<6){
                    password.setError("password too short");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                    return;
                }
                else{

                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                User user=new User(Fname,Lname,number,Email,"User","No Image");
                                firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Personal details").setValue(user);
                                Toast.makeText(ProfileActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(ProfileActivity.this, MapActivity.class);
                                //i.putExtra("phone",number);
                                startActivity(i);
                                finish();
                                loader.dismiss();
                            }
                            else{
                                Toast.makeText(ProfileActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }

            }
        });



    }
}