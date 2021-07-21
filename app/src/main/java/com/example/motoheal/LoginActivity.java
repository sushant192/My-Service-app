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

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    TextView signin,forgot;
    CheckBox showpassword;

    FirebaseAuth auth;
    ProgressDialog loader;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signin=findViewById(R.id.login1);
        forgot=findViewById(R.id.forgot);
        showpassword=findViewById(R.id.showpassword);

        auth=FirebaseAuth.getInstance();
        loader=new ProgressDialog(this);
        firebaseDatabase=FirebaseDatabase.getInstance();

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=email.getText().toString();
                String pas=password.getText().toString();

                if(TextUtils.isEmpty(em)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pas)){
                    password.setError("Password is required");
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

                                firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChild(auth.getUid())){

                                            Intent intentResident = new Intent(LoginActivity.this, MapActivity.class);
                                            //intentResident.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intentResident);
                                            finish();
                                            loader.dismiss();
                                        }
                                        else{
                                            Toast.makeText(LoginActivity.this, "Failed Login. Please Try Again", Toast.LENGTH_SHORT).show();
                                            loader.dismiss();
                                            return;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                /*firebaseDatabase.getReference().child("Users").child(RegisteredUserID).child("Personal details").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if (snapshot.hasChild("type")){

                                            //error because of null in usertype
                                            String userType = snapshot.child("type").getValue().toString();
                                            if(userType.equals("User") && userType!=null){
                                                Intent intentResident = new Intent(LoginActivity.this, MapActivity.class);
                                                //intentResident.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intentResident);
                                                finish();
                                                loader.dismiss();
                                            }
                                            else {
                                                Toast.makeText(LoginActivity.this, "Failed Login. Please Try Again", Toast.LENGTH_SHORT).show();
                                                loader.dismiss();
                                                return;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });*/

                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}