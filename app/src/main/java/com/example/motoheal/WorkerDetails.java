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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class WorkerDetails extends AppCompatActivity {

    EditText workername,workerphone,workeraddress;
    TextView accept,workerdob,Idpic;
    Spinner spinner1;
    ImageView image1,image2;
    Uri workerid,workerid1;
    String IdType;

    Calendar calendar;
    DatePickerDialog datePickerDialog;

    RadioGroup radioGroup;
    RadioButton radioButton,radioButton1;

    private static final int RequestCode=123;
    private static final int RequestCode1=1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);


        workername=findViewById(R.id.workername);
        workerphone=findViewById(R.id.workerphone2);
        workeraddress=findViewById(R.id.workeraddress);
        workerdob=findViewById(R.id.workerdob);
        accept=findViewById(R.id.accept);
        spinner1=findViewById(R.id.spinner1);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        Idpic=findViewById(R.id.idpic);

        radioGroup=findViewById(R.id.radiogroup);
        radioButton=findViewById(R.id.radiobutton);
        radioButton1=findViewById(R.id.radiobutton1);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.id, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdType=parent.getItemAtPosition(position).toString();
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


        workerdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar=Calendar.getInstance();
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int m=calendar.get(Calendar.MONTH);
                int y=calendar.get(Calendar.YEAR);

                datePickerDialog=new DatePickerDialog(WorkerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        workerdob.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },y,m,day);
                datePickerDialog.show();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked=radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(checked);
                String r=radioButton.getText().toString();

                String Workername=workername.getText().toString();
                String Workerphone=workerphone.getText().toString();
                String Workeraddress=workeraddress.getText().toString();
                String Workerdob=workerdob.getText().toString();

                String fullname=getIntent().getStringExtra("fullname");
                String phonenumber=getIntent().getStringExtra("phonenumber");
                String gmailid=getIntent().getStringExtra("gmailid");
                String address=getIntent().getStringExtra("address");
                String dob=getIntent().getStringExtra("dob");
                String partnertype=getIntent().getStringExtra("partnertype");
                String partnertype1=getIntent().getStringExtra("partnertype1");
                String imagedata=getIntent().getStringExtra("imagedata");
                String imagedata1=getIntent().getStringExtra("imagedata1");

                String shopname=getIntent().getStringExtra("shopname");
                String shopnumber=getIntent().getStringExtra("shopnumber");
                String shopgid=getIntent().getStringExtra("shopgid");
                String shopaddress=getIntent().getStringExtra("shopaddress");
                String noofworkers=getIntent().getStringExtra("noofworkers");
                String workshopname=getIntent().getStringExtra("workshopname");
                String Gst=getIntent().getStringExtra("Gst");

                if(TextUtils.isEmpty(Workername)){
                    workername.setError("name is required");
                    return;
                }
                if (TextUtils.isEmpty(Workerphone)){
                    workerphone.setError("number is required");
                    return;
                }
                if(TextUtils.isEmpty(Workeraddress)){
                    workeraddress.setError("Address is required");
                    return;
                }
                if (workerid==null){
                    Idpic.setError("Photo is required");
                    return;
                }
                if (workerid1==null){
                    Idpic.setError("Photo is required");
                    return;
                }
                if(TextUtils.isEmpty(Workerdob)){
                    workerdob.setError("DOB is required");
                    return;
                }
                else {
                    Intent i=new Intent(WorkerDetails.this,PartnerRegister.class);
                    i.putExtra("Workername",Workername);
                    i.putExtra(" Workerphone", Workerphone);
                    i.putExtra("Workeraddress",Workeraddress);
                    i.putExtra("Workerdob",Workerdob);
                    i.putExtra("Workergender",r);
                    i.putExtra("idtype",IdType);
                    i.putExtra("workeridpic",String.valueOf(workerid));
                    i.putExtra("workeridpic1",String.valueOf(workerid1));

                    i.putExtra("shopname",shopname);
                    i.putExtra("shopnumber",shopnumber);
                    i.putExtra("shopgid",shopgid);
                    i.putExtra("shopaddress",shopaddress);
                    i.putExtra("noofworkers",noofworkers);
                    i.putExtra("workshopname",workshopname);
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
            workerid=data.getData();
            image1.setImageURI(workerid);
        }
        if(requestCode==RequestCode1 && resultCode==RESULT_OK && data!=null){
            workerid1=data.getData();
            image2.setImageURI(workerid1);
        }
    }
}