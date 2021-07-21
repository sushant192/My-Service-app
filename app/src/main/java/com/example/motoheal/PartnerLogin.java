package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PartnerLogin extends AppCompatActivity {

    EditText loginid,loginpassword;
    TextView signin,newUser;
    CheckBox showpassword;

    FirebaseAuth auth;
    ProgressDialog loader;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_login);

        newUser=findViewById(R.id.newuser);
        loginid=findViewById(R.id.logingid);
        loginpassword=findViewById(R.id.loginpassword);
        signin=findViewById(R.id.signin);
        showpassword=findViewById(R.id.showpassword);

        firebaseDatabase=FirebaseDatabase.getInstance();
        loader=new ProgressDialog(this);
        auth=FirebaseAuth.getInstance();

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    loginpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    loginpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=loginid.getText().toString();
                String pas=loginpassword.getText().toString();

                if(TextUtils.isEmpty(em)){
                    loginid.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pas)){
                    loginpassword.setError("Password is required");
                    return;
                }
                else{
                    loader.setMessage("LogIn in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    auth.signInWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                String RegisteredUserID = currentUser.getUid();

                                firebaseDatabase.getReference().child("Partners").child("partners").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if(snapshot.hasChild(auth.getUid())){
                                            Intent intentResident = new Intent(PartnerLogin.this, PartnerMapActivity.class);
                                            //intentResident.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intentResident);
                                            finish();
                                            loader.dismiss();
                                        }
                                        else {
                                            Toast.makeText(PartnerLogin.this, "Failed Login. Please Try Again", Toast.LENGTH_SHORT).show();
                                            loader.dismiss();
                                            return;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                            else{
                                Toast.makeText(PartnerLogin.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PartnerLogin.this,OwnerDetails.class);
                startActivity(i);
                finish();
            }
        });
    }
}