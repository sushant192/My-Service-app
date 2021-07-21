package com.example.motoheal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GarageDetails extends AppCompatActivity {

    EditText shopName,shopNumber,shopAddress,shopgid,NoOfWorkers,workshopName,Gstno;
    TextView next1;

    ImageView image1,image2,image3,image4;
    Uri imagedata,imagedata1,imagedata2,imagedata3;

    private static final int RequestCode=123;
    private static final int RequestCode1=1234;
    private static final int RequestCode2=12345;
    private static final int RequestCode3=12346;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_details);

        shopName=findViewById(R.id.shopname);
        shopNumber=findViewById(R.id.shopnumber);
        shopAddress=findViewById(R.id.shopaddress);
        shopgid=findViewById(R.id.shopgid);
        NoOfWorkers=findViewById(R.id.noofworkers);
        workshopName=findViewById(R.id.workshopname);
        Gstno=findViewById(R.id.gstno);
        next1=findViewById(R.id.next1);

        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode1);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode2);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode3);
            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Shopname=shopName.getText().toString();
                String Shopnumber=shopNumber.getText().toString();
                String Shopaddress=shopAddress.getText().toString();
                String Shopgid=shopgid.getText().toString();
                String Workshop=workshopName.getText().toString();
                String Gst=Gstno.getText().toString();
                if (Shopgid==null){
                    Shopgid="Null";
                }
                String noofworkers=NoOfWorkers.getText().toString();


                String fullname=getIntent().getStringExtra("fullname");
                String phonenumber=getIntent().getStringExtra("phonenumber");
                String gmailid=getIntent().getStringExtra("gmailid");
                String address=getIntent().getStringExtra("address");
                String dob=getIntent().getStringExtra("dob");
                String partnertype=getIntent().getStringExtra("partnertype");
                String partnertype1=getIntent().getStringExtra("partnertype1");
                String imagedata=getIntent().getStringExtra("imagedata");
                String imagedata1=getIntent().getStringExtra("imagedata1");

                if(TextUtils.isEmpty(Shopname)){
                    shopName.setError("name is required");
                    return;
                }
                if (TextUtils.isEmpty(Shopnumber)){
                    shopNumber.setError("number is required");
                    return;
                }
                if(TextUtils.isEmpty(Shopaddress)){
                    shopAddress.setError("Address is required");
                    return;
                }
                if(TextUtils.isEmpty(noofworkers)){
                    NoOfWorkers.setError("DOB is required");
                    return;
                }
                if (TextUtils.isEmpty(Workshop)){
                    workshopName.setError("Required");
                    return;
                }
                if(TextUtils.isEmpty(Gst)){
                    Gstno.setError("required");
                    return;
                }
                else{
                    Intent i=new Intent(GarageDetails.this,WorkerDetails.class);
                    i.putExtra("shopname",Shopname);
                    i.putExtra("shopnumber",Shopnumber);
                    i.putExtra("shopgid",Shopgid);
                    i.putExtra("shopaddress",Shopaddress);
                    i.putExtra("noofworkers",noofworkers);
                    i.putExtra("workshopname",Workshop);
                    i.putExtra("Gst",Gst);

                    i.putExtra("fullname",fullname);
                    i.putExtra("phonenumber",phonenumber);
                    i.putExtra("gmailid",gmailid);
                    i.putExtra("address",address);
                    i.putExtra("dob",dob);
                    i.putExtra("partnertype",partnertype);
                    i.putExtra("partnertype1",partnertype1);
                    i.putExtra("imagedata",imagedata);
                    i.putExtra("imagedata1",imagedata1);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RequestCode && resultCode==RESULT_OK && data!=null){
            imagedata=data.getData();
            image1.setImageURI(imagedata);
        }
        if(requestCode==RequestCode1 && resultCode==RESULT_OK && data!=null){
            imagedata1=data.getData();
            image2.setImageURI(imagedata1);
        }
        if(requestCode==RequestCode2 && resultCode==RESULT_OK && data!=null){
            imagedata2=data.getData();
            image3.setImageURI(imagedata2);
        }
        if(requestCode==RequestCode3 && resultCode==RESULT_OK && data!=null){
            imagedata3=data.getData();
            image4.setImageURI(imagedata3);
        }
    }
}