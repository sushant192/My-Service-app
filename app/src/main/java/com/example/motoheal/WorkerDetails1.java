package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkerDetails1 extends AppCompatActivity {

    EditText workername1,workerphone1,workerid1,workeraddress1;
    TextView accept1,workerdob1,Idpic;

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    Spinner spinner1;
    ImageView image1,image2;
    Uri workerid,workerid2;

    RadioGroup radioGroup1;
    RadioButton radioButton2,radioButton3;

    String IdType;

    FirebaseAuth auth;
    ProgressDialog loader;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    private static final int RequestCode=123;
    private static final int RequestCode1=1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details1);

        workername1=findViewById(R.id.workername1);
        workerphone1=findViewById(R.id.workerphone1);
        spinner1=findViewById(R.id.spinner1);

        workeraddress1=findViewById(R.id.workeraddress1);
        workerdob1=findViewById(R.id.workerdob1);
        accept1=findViewById(R.id.accept1);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        Idpic=findViewById(R.id.idpic);

        radioGroup1=findViewById(R.id.radiogroup1);
        radioButton2=findViewById(R.id.radiobutton2);
        radioButton3=findViewById(R.id.radiobutton3);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        loader=new ProgressDialog(this);
        firebaseStorage = FirebaseStorage.getInstance();

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

        workerdob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar=Calendar.getInstance();
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int m=calendar.get(Calendar.MONTH);
                int y=calendar.get(Calendar.YEAR);

                datePickerDialog=new DatePickerDialog(WorkerDetails1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        workerdob1.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },y,m,day);
                datePickerDialog.show();
            }
        });

        accept1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String WorkerName1 = workername1.getText().toString();
                final String Workerphone1 = workerphone1.getText().toString();
                //final String Workerid1 = workerid1.getText().toString();
                final String Workeraddress1 = workeraddress1.getText().toString();
                final String Workerdob1 = workerdob1.getText().toString();

                int checked = radioGroup1.getCheckedRadioButtonId();
                radioButton2 = findViewById(checked);
                final String r = radioButton2.getText().toString();

                if (TextUtils.isEmpty(WorkerName1)) {
                    workername1.setError("name is required");
                    return;
                }
                if (TextUtils.isEmpty(Workerphone1)) {
                    workerphone1.setError("number is required");
                    return;
                }
                if (TextUtils.isEmpty(Workeraddress1)) {
                    workeraddress1.setError("Address is required");
                    return;
                }
                if (workerid==null){
                    Idpic.setError("Photo is required");
                    return;
                }
                if (workerid2==null){
                    Idpic.setError("Photo is required");
                    return;
                }
                if (TextUtils.isEmpty(Workerdob1)) {
                    workerdob1.setError("DOB is required");
                    return;
                } else {

                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    final String PendingId = firebaseDatabase.getReference().child("Pending Requests").push().getKey();
                    final String[] url = new String[2];

                    final StorageReference storageReference = firebaseStorage.getReference().child("Pic").child(PendingId).child(String.valueOf(System.currentTimeMillis()));
                    storageReference.putFile(Uri.parse(String.valueOf(workerid))).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        url[0] = uri.toString();

                                        final StorageReference storageReference1 = firebaseStorage.getReference().child("Pic").child(PendingId).child(String.valueOf(System.currentTimeMillis()));
                                        storageReference1.putFile(Uri.parse(String.valueOf(workerid2))).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            url[1] = uri.toString();

                                                            Calendar calendar = Calendar.getInstance();
                                                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                                            String date = simpleDateFormat.format(calendar.getTime());

                                                            Worker w = new Worker(WorkerName1, Workerphone1, IdType, Workeraddress1, Workerdob1, r, url[0], url[1]);
                                                            firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Worker Details").push().setValue(w).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    loader.dismiss();
                                                                    Intent i = new Intent(WorkerDetails1.this, CoWorkers.class);
                                                                    startActivity(i);
                                                                    finish();
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
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
            workerid2=data.getData();
            image2.setImageURI(workerid2);
        }
    }
}