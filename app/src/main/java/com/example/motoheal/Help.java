package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView forgotpassword,transfer,somequestions,otherquestions,questions,questions1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        forgotpassword=findViewById(R.id.fpassword);
        transfer=findViewById(R.id.tmoney);
        
    }
}