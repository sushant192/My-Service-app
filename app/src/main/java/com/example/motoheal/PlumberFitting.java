package com.example.motoheal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class PlumberFitting extends AppCompatActivity {

    EditText TotalRooms,HowmanyDays;
    Button confirm2;
    ImageView image7,image8,image9;
    Uri imagedata1,imagedata2,imagedata3;


    private static final int RequestCode=123;
    private static final int RequestCode1=1234;
    private static final int RequestCode2=12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber_fitting);

        TotalRooms=findViewById(R.id.totalrooms);
        HowmanyDays=findViewById(R.id.howmanydays);
        image7=findViewById(R.id.image7);
        image8=findViewById(R.id.image8);
        image9=findViewById(R.id.image9);
        confirm2=findViewById(R.id.confirm2);

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode);
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode1);
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode2);
            }
        });


        confirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String total1=TotalRooms.getText().toString();
                final String day1=HowmanyDays.getText().toString();

                //String da=new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

                if(TextUtils.isEmpty(total1)){
                    TotalRooms.setError("required");
                    return;
                }
                if(TextUtils.isEmpty(day1)){
                    HowmanyDays.setError("required");
                    return;
                }

                int s=Integer.parseInt(day1);

                if (s<2){
                    HowmanyDays.setError("Minimum 2 days");
                    return;
                }

                /*if (imagedata1!=null){
                    ima1.add(imagedata1);
                }
                if (imagedata2!=null){
                    ima1.add(imagedata2);
                }
                if (imagedata3!=null){
                    ima1.add(imagedata3);
                }*/

                Intent i=new Intent(PlumberFitting.this,NearbyPlumbers1.class);
                i.putExtra("imagedata1",String.valueOf(imagedata1));
                i.putExtra("imagedata2",String.valueOf(imagedata2));
                i.putExtra("imagedata3",String.valueOf(imagedata3));
                i.putExtra("TotalRooms",total1);
                i.putExtra("HowmanyDays",day1);
                startActivity(i);
                finish();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RequestCode && resultCode==RESULT_OK && data!=null){
            imagedata1=data.getData();
            image7.setImageURI(imagedata1);
        }
        if(requestCode==RequestCode1 && resultCode==RESULT_OK && data!=null){
            imagedata2=data.getData();
            image8.setImageURI(imagedata2);
        }
        if(requestCode==RequestCode2 && resultCode==RESULT_OK && data!=null){
            imagedata3=data.getData();
            image9.setImageURI(imagedata3);
        }
    }
}