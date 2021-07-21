package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PainterActivity extends AppCompatActivity {

    ImageView image1,image2,image3,image4,image5,image6;
    EditText totalwalls,days;
    Button confirm;
    Uri imagedata,imagedata1,imagedata2,imagedata3,imagedata4,imagedata5;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;


    ProgressDialog progressDialog;

    private static final int RequestCode=123;
    private static final int RequestCode1=1234;
    private static final int RequestCode2=12345;
    private static final int RequestCode3=321;
    private static final int RequestCode4=3210;
    private static final int RequestCode5=3217;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter);


        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);
        image5=findViewById(R.id.image5);
        image6=findViewById(R.id.image6);

        totalwalls=findViewById(R.id.totalwalls);
        days=findViewById(R.id.days);
        confirm=findViewById(R.id.confirm);

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

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode4);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode5);
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage= FirebaseStorage.getInstance();


        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating Your Profile");
        progressDialog.setCancelable(false);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String total1=totalwalls.getText().toString();
                final String day1=days.getText().toString();

                //String da=new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

                if(TextUtils.isEmpty(total1)){
                    totalwalls.setError("required");
                    return;
                }
                if(TextUtils.isEmpty(day1)){
                    days.setError("required");
                    return;
                }

                int s=Integer.parseInt(day1);

                if (s<2){
                    days.setError("Minimum 2 days");
                    return;
                }



                Intent i=new Intent(PainterActivity.this,NearbyPainters.class);
                i.putExtra("imagedata",String.valueOf(imagedata));
                i.putExtra("imagedata1",String.valueOf(imagedata1));
                i.putExtra("imagedata2",String.valueOf(imagedata2));
                i.putExtra("imagedata3",String.valueOf(imagedata3));
                i.putExtra("imagedata4",String.valueOf(imagedata4));
                i.putExtra("imagedata5",String.valueOf(imagedata5));
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
        if(requestCode==RequestCode4 && resultCode==RESULT_OK && data!=null){
            imagedata4=data.getData();
            image5.setImageURI(imagedata4);
        }
        if(requestCode==RequestCode5 && resultCode==RESULT_OK && data!=null){
            imagedata5=data.getData();
            image6.setImageURI(imagedata5);
        }
    }
}