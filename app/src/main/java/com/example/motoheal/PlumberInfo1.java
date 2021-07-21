package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PlumberInfo1 extends AppCompatActivity {

    TextView garageName,grab,sent,address,noofworker,gphone,available,ratings;
    Dialog dialog;
    ImageView pro;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber_info1);

        final String name=getIntent().getStringExtra("name");
        final String owner=getIntent().getStringExtra("owner");

        final String Service=getIntent().getStringExtra("TotalRooms");
        final String Issues=getIntent().getStringExtra("HowmanyDays");
        final String imagedata1=getIntent().getStringExtra("imagedata1");
        final String imagedata2=getIntent().getStringExtra("imagedata2");
        final String imagedata3=getIntent().getStringExtra("imagedata3");


        garageName=findViewById(R.id.gname1);
        garageName.setText(name);
        grab=findViewById(R.id.grab3);
        pro=findViewById(R.id.pro);
        address=findViewById(R.id.address1);
        noofworker=findViewById(R.id.noofworker);
        gphone=findViewById(R.id.gphone);
        available=findViewById(R.id.availableworkers);
        ratings=findViewById(R.id.ratings);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        firebaseDatabase.getReference().child("Partners").child("partners").child(owner).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                float Sum=0;
                String s=snapshot.child("Business Details").child("profilepic").getValue().toString();
                String s1=snapshot.child("Business Details").child("shopAddress").getValue().toString();
                String s2=snapshot.child("Business Details").child("noofworkers").getValue().toString();
                String s3=snapshot.child("Business Details").child("shopNumber").getValue().toString();

                for (DataSnapshot snapshot1:snapshot.child("Ratings").getChildren()){
                    String g=snapshot1.child("stars").getValue().toString();
                    float h=Float.parseFloat(g);
                    Sum=Sum+h;
                }

                float j=Sum/snapshot.child("Ratings").getChildrenCount();
                ratings.setText(String.valueOf(j));

                address.setText(s1);
                long d=snapshot.child("Worker Details").getChildrenCount()+1;
                noofworker.setText(String.valueOf(d));
                available.setText(String.valueOf(d));
                gphone.setText(s3);
                Picasso.get().load(s).placeholder(R.drawable.avatar).into(pro);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.request_sent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        sent=dialog.findViewById(R.id.sent);

        grab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                final String date=simpleDateFormat.format(calendar.getTime());

                    final String k=firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").push().getKey();
                    Request request1=new Request(name,owner,k,"","",date,"",Issues);
                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Info").setValue(request1);
                    PlumberRequest1 request=new PlumberRequest1(name,owner,k,"","",Service,Issues);
                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Details").setValue(request);

                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Personal details").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String name=snapshot.child("firstName").getValue().toString();
                            String number=snapshot.child("phoneNumber").getValue().toString();
                            String pic=snapshot.child("profilePic").getValue().toString();

                            User1 u1=new User1(name,"",number,"","",auth.getUid(),k,pic,"Plumber2",date,"",Issues);
                            firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Info").setValue(u1);
                            PlumberPartnerRequest1 u=new PlumberPartnerRequest1(name,"",number,"","",auth.getUid(),k,Service,Issues);
                            firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Details").setValue(u);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    if (imagedata1!=null){
                        final StorageReference storageReference=firebaseStorage.getReference().child("RoomPic").child(auth.getUid()).child(String.valueOf(System.currentTimeMillis()));
                        storageReference.putFile(Uri.parse(imagedata1)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if(task.isSuccessful()){
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String url=uri.toString();
                                            //User user=new User(n1,number,url);

                                            /*HashMap<String,String> hashMap=new HashMap<>();
                                            hashMap.put("RoomImage1",url);*/
                                            firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Images").child("RoomImage1").setValue(url);
                                            firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Images").child("RoomImage1").setValue(url);
                                        }
                                    });
                                }
                            }
                        });
                    }
                    else {

                        //HashMap<String,String> hashMap=new HashMap<>();
                        //hashMap.put("RoomImage1","No Image");

                        String ur="No Image";
                        firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Images").child("RoomImage1").setValue(ur);
                        firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Images").child("RoomImage1").setValue(ur);
                    }

                if (imagedata2!=null){
                    final StorageReference storageReference=firebaseStorage.getReference().child("RoomPic").child(auth.getUid()).child(String.valueOf(System.currentTimeMillis()));
                    storageReference.putFile(Uri.parse(imagedata2)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String url1=uri.toString();
                                        //User user=new User(n1,number,url);

                                        firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Images").child("RoomImage2").setValue(url1);
                                        firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Images").child("RoomImage2").setValue(url1);
                                    }
                                });
                            }
                        }
                    });
                }
                else {

                    String ur="No Image";

                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Images").child("RoomImage2").setValue(ur);
                    firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Images").child("RoomImage2").setValue(ur);
                }

                if (imagedata3!=null){
                    final StorageReference storageReference=firebaseStorage.getReference().child("RoomPic").child(auth.getUid()).child(String.valueOf(System.currentTimeMillis()));
                    storageReference.putFile(Uri.parse(imagedata3)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String url2=uri.toString();
                                        //User user=new User(n1,number,url);

                                        firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Images").child("RoomImage3").setValue(url2);
                                        firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Images").child("RoomImage3").setValue(url2);
                                    }
                                });
                            }
                        }
                    });
                }
                else {

                    String ur="No Image";

                    firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Requests").child(k).child("Images").child("RoomImage3").setValue(ur);
                    firebaseDatabase.getReference().child("Requests").child(owner).child(k).child("Images").child("RoomImage2").setValue(ur);
                }

                dialog.show();

            }
        });


        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlumberInfo1.this,MapActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
    }
}