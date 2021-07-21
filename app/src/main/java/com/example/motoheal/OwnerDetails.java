package com.example.motoheal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class OwnerDetails extends AppCompatActivity{

    EditText fullname,phonenumber,gmailid,address;
    TextView dob,next,service1,idpic;
    Spinner spinner,spinner1;
    String partnerType,partnerType1;
    ImageView image1,image2;
    Uri imagedata,imagedata1;

    Calendar calendar;
    DatePickerDialog datePickerDialog;

    private static final int RequestCode=123;
    private static final int RequestCode1=1234;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);

        fullname=findViewById(R.id.fullname);
        phonenumber=findViewById(R.id.ownerphonenumber);
        gmailid=findViewById(R.id.ownergid);
        address=findViewById(R.id.owneraddress);
        spinner = findViewById(R.id.spinner);
        spinner1=findViewById(R.id.spinner1);
        service1=findViewById(R.id.service1);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        idpic=findViewById(R.id.idpic);

        dob=findViewById(R.id.dob);
        next=findViewById(R.id.next);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                partnerType=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.id, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partnerType1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar= Calendar.getInstance();
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int m=calendar.get(Calendar.MONTH);
                int y=calendar.get(Calendar.YEAR);

                datePickerDialog=new DatePickerDialog(OwnerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },y,m,day);
                datePickerDialog.show();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Fullname=fullname.getText().toString();
                String Phonenumber=phonenumber.getText().toString();
                String Gmailid=gmailid.getText().toString();
                String Address=address.getText().toString();
                String DOB=dob.getText().toString();

                if(TextUtils.isEmpty(Fullname)){
                    fullname.setError("name is required");
                    return;
                }
                if (TextUtils.isEmpty(Phonenumber)){
                    phonenumber.setError("number is required");
                    return;
                }
                if(TextUtils.isEmpty(Gmailid)){
                    gmailid.setError("mail is required");
                    return;
                }
                if(TextUtils.isEmpty(DOB)){
                    dob.setError("DOB is required");
                    return;
                }
                if(TextUtils.isEmpty(Address)){
                    address.setError("Address is required");
                    return;
                }
                if (imagedata==null){
                    idpic.setError("Photo is required");
                    return;
                }
                if (imagedata1==null){
                    idpic.setError("Photo is required");
                    return;
                }
                else{
                    Intent i=new Intent(OwnerDetails.this,GarageDetails.class);
                    i.putExtra("fullname",Fullname);
                    i.putExtra("phonenumber",Phonenumber);
                    i.putExtra("gmailid",Gmailid);
                    i.putExtra("imagedata",String.valueOf(imagedata));
                    i.putExtra("imagedata1",String.valueOf(imagedata1));
                    i.putExtra("address",Address);
                    i.putExtra("dob",DOB);
                    i.putExtra("partnertype",partnerType);
                    i.putExtra("partnertype1",partnerType1);
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
    }
}