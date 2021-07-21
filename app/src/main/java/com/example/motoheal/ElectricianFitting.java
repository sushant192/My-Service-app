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

public class ElectricianFitting extends AppCompatActivity {

    EditText TotalRooms1,HowmanyDays1;
    Button pay3,confirm3;
    ImageView img1,img2,img3;
    Uri imgdata6,imgdata7,imgdata8;

    private static final int RequestCode=123;
    private static final int RequestCode1=1234;
    private static final int RequestCode2=12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician_fitting);

        TotalRooms1=findViewById(R.id.rooms1);
        HowmanyDays1=findViewById(R.id.days1);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        confirm3=findViewById(R.id.confirm3);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode1);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode2);
            }
        });



        confirm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String total1=TotalRooms1.getText().toString();
                final String day1=HowmanyDays1.getText().toString();

                if(TextUtils.isEmpty(total1)){
                    TotalRooms1.setError("required");
                    return;
                }
                if(TextUtils.isEmpty(day1)){
                    HowmanyDays1.setError("required");
                    return;
                }

                int s=Integer.parseInt(day1);

                if (s<2){
                    HowmanyDays1.setError("Minimum 2 days");
                    return;
                }

                Intent i=new Intent(ElectricianFitting.this,NearbyElectricians1.class);
                i.putExtra("imagedata1",String.valueOf(imgdata6));
                i.putExtra("imagedata2",String.valueOf(imgdata7));
                i.putExtra("imagedata3",String.valueOf(imgdata8));
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
            imgdata6=data.getData();
            img1.setImageURI(imgdata6);
        }
        if(requestCode==RequestCode1 && resultCode==RESULT_OK && data!=null){
            imgdata7=data.getData();
            img2.setImageURI(imgdata7);
        }
        if(requestCode==RequestCode2 && resultCode==RESULT_OK && data!=null){
            imgdata8=data.getData();
            img3.setImageURI(imgdata8);
        }
    }
}