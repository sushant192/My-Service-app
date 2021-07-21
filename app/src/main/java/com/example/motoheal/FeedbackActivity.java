package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    EditText name,email,message;
    TextView submit;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        name=findViewById(R.id.name);
        email=findViewById(R.id.Email);
        message=findViewById(R.id.message);
        submit=findViewById(R.id.submit);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name=name.getText().toString();
                String Email=email.getText().toString();
                String Message=message.getText().toString();

                if (TextUtils.isEmpty(Name)){
                    name.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(Email)){
                    email.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(Message)){
                    message.setError("Required");
                    return;
                }

                Feedback feedback=new Feedback(Name,Email,Message,auth.getUid());
                firebaseDatabase.getReference().child("Feedbacks").push().setValue(feedback);
                String key=firebaseDatabase.getReference().child("Admin").child("Notifications").child("Feedbacks").push().getKey();

                Notification1 notification1=new Notification1("Feeback","Feedback Message sent by a user",auth.getUid(),"","","",key);
                firebaseDatabase.getReference().child("Admin").child("Notifications").child("Feedbacks").child(key).setValue(notification1);
                Intent i=new Intent(FeedbackActivity.this,MapActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}