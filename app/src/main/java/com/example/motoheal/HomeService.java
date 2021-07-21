package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeService extends AppCompatActivity {

    TextView plumber,electrician,painter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_service);

        plumber=findViewById(R.id.plumber);
        electrician=findViewById(R.id.electrician);
        painter=findViewById(R.id.painter);


        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeService.this,PlumberActivity.class);
                startActivity(i);
                finish();
            }
        });

        electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeService.this,ElectricianActivity.class);
                startActivity(i);
                finish();
            }
        });

        painter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeService.this,PainterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}