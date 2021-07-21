package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

public class PartnerRegister extends AppCompatActivity{

    EditText gid,password,repassword;
    TextView Register;
    CheckBox terms;

    FirebaseAuth auth;
    ProgressDialog loader;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_register);

        gid = findViewById(R.id.gid);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        Register = findViewById(R.id.regnow);
        terms=findViewById(R.id.terms);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        loader = new ProgressDialog(this);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = gid.getText().toString();
                final String Password = password.getText().toString();
                String RePassword = repassword.getText().toString();
                final String type = "Partner";


                final String fullname = getIntent().getStringExtra("fullname");
                final String phonenumber = getIntent().getStringExtra("phonenumber");
                String gmailid = getIntent().getStringExtra("gmailid");
                final String address = getIntent().getStringExtra("address");
                final String dob = getIntent().getStringExtra("dob");
                final String partnertype = getIntent().getStringExtra("partnertype");
                final String partnertype1 = getIntent().getStringExtra("partnertype1");
                final String imagedata = getIntent().getStringExtra("imagedata");
                final String imagedata1 = getIntent().getStringExtra("imagedata1");

                final String shopname = getIntent().getStringExtra("shopname");
                final String shopnumber = getIntent().getStringExtra("shopnumber");
                final String shopgid = getIntent().getStringExtra("shopgid");
                final String shopaddress = getIntent().getStringExtra("shopaddress");
                final String noofworkers = getIntent().getStringExtra("noofworkers");
                final String workshopname = getIntent().getStringExtra("workshopname");
                final String Gst = getIntent().getStringExtra("Gst");

                final String Workername = getIntent().getStringExtra("Workername");
                final String Workerphone = getIntent().getStringExtra("Workerphone");
                final String Workeraddress = getIntent().getStringExtra("Workeraddress");
                final String Workerdob = getIntent().getStringExtra("Workerdob");
                final String Workergender = getIntent().getStringExtra("Workergender");
                final String idtype = getIntent().getStringExtra("idtype");
                final String workeridpic = getIntent().getStringExtra("workeridpic");
                final String workeridpic1 = getIntent().getStringExtra("workeridpic1");


                if (TextUtils.isEmpty(Email)) {
                    gid.setError("email is required");
                    return;
                }
                if (Password.length() < 6) {
                    password.setError("password too short");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    password.setError("Password is required");
                    return;
                }
                if (TextUtils.isEmpty(RePassword)) {
                    repassword.setError("Password is required");
                    return;
                }
                if (!terms.isChecked()){
                    terms.setError("Required");
                    return;
                }
                if (!Password.equals(RePassword)) {
                    repassword.setError("Password is required");
                    return;
                }
                else {

                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    final String[] url = new String[4];

                    final String PendingId = firebaseDatabase.getReference().child("Pending Requests").push().getKey();

                    final StorageReference storageReference = firebaseStorage.getReference().child("Pic").child(PendingId).child(String.valueOf(System.currentTimeMillis()));
                    storageReference.putFile(Uri.parse(imagedata)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        url[0] = uri.toString();

                                        final StorageReference storageReference1 = firebaseStorage.getReference().child("Pic").child(PendingId).child(String.valueOf(System.currentTimeMillis()));
                                        storageReference1.putFile(Uri.parse(imagedata1)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                if (task.isSuccessful()){
                                                    storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            url[1] = uri.toString();

                                                            final StorageReference storageReference2 = firebaseStorage.getReference().child("Pic").child(PendingId).child(String.valueOf(System.currentTimeMillis()));
                                                            storageReference2.putFile(Uri.parse(workeridpic)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                                    if (task.isSuccessful()){
                                                                        storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                            @Override
                                                                            public void onSuccess(Uri uri) {
                                                                                url[2] = uri.toString();

                                                                                final StorageReference storageReference3 = firebaseStorage.getReference().child("Pic").child(PendingId).child(String.valueOf(System.currentTimeMillis()));
                                                                                storageReference3.putFile(Uri.parse(workeridpic1)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                                                        if (task.isSuccessful()){
                                                                                            storageReference3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                @Override
                                                                                                public void onSuccess(Uri uri) {
                                                                                                    url[3] = uri.toString();

                                                                                                    Calendar calendar= Calendar.getInstance();
                                                                                                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                                                                                                    String date=simpleDateFormat.format(calendar.getTime());

                                                                                                    Pending pending = new Pending(fullname, phonenumber, Email, Password, type, address, dob, partnertype, partnertype1, url[0], url[1], shopname, shopnumber, shopgid, shopaddress, noofworkers, "No Image", workshopname, Gst, Workername, "", Workeraddress, Workerdob, Workergender, idtype, url[2], url[3], PendingId, "",date);
                                                                                                    firebaseDatabase.getReference().child("Pending Requests").child(PendingId).setValue(pending).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onSuccess(Void aVoid) {
                                                                                                            Intent i = new Intent(PartnerRegister.this, Verification.class);
                                                                                                            startActivity(i);
                                                                                                            finish();
                                                                                                            loader.dismiss();
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
                                                    });
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });


                    Calendar calendar= Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                    String date=simpleDateFormat.format(calendar.getTime());

                    Notification1 notification1=new Notification1("Registration Request","Request for verification has been received","","",date,"",PendingId);
                    firebaseDatabase.getReference().child("Admin").child("Notifications").child("Registration Requests").child(PendingId).setValue(notification1);

                }
            }
        });







                    /*auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                String RegisteredUserID = currentUser.getUid();

                                if (partnertype.contentEquals("Garage Owner")){
                                    Business business=new Business(shopname,shopnumber,shopgid,shopaddress,noofworkers,RegisteredUserID);
                                    firebaseDatabase.getReference().child("Partners").child("Business").child("Garage Services").push().setValue(business);
                                }
                                if (partnertype.contentEquals("Plumber")){
                                    Business business1=new Business(shopname,shopnumber,shopgid,shopaddress,noofworkers,RegisteredUserID);
                                    firebaseDatabase.getReference().child("Partners").child("Business").child("Plumbing Business").push().setValue(business1);
                                }
                                if (partnertype.contentEquals("Electrician")){
                                    Business business2=new Business(shopname,shopnumber,shopgid,shopaddress,noofworkers,RegisteredUserID);
                                    firebaseDatabase.getReference().child("Partners").child("Business").child("Electrician business").push().setValue(business2);
                                }
                                if (partnertype.contentEquals("Painter")){
                                    Business business3=new Business(shopname,shopnumber,shopgid,shopaddress,noofworkers,RegisteredUserID);
                                    firebaseDatabase.getReference().child("Partners").child("Business").child("Painting business").push().setValue(business3);
                                }

                                Partner partner=new Partner(fullname,phonenumber,Email,Password,type,address,dob,partnertype,partnertype1,imagedata,imagedata1);
                                firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Personal Details").setValue(partner);

                                Service service=new Service(shopname,shopnumber,shopgid,shopaddress,noofworkers,"No Image");
                                firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Business Details").setValue(service);

                                Worker worker=new Worker(Workername,Workerphone,Workerid,Workeraddress,Workerdob,Workergender);
                                firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Worker Details").push().setValue(worker);

                                Worker1 worker1=new Worker1(Workername,Workerphone,Workerid,Workeraddress,Workerdob,Workergender,RegisteredUserID);
                                firebaseDatabase.getReference().child("Partners").child("Workers").push().setValue(worker1);

                                Toast.makeText(PartnerRegister.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(PartnerRegister.this, PartnerMapActivity.class);
                                //i.putExtra("phone",number);
                                startActivity(i);
                                finish();
                                loader.dismiss();
                            }
                            else{
                                Toast.makeText(PartnerRegister.this,"Registration failed",Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });*/

    }

}