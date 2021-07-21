package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class CompleteProfile1 extends AppCompatActivity {

    ImageView imageView;
    TextView profilename,profilephoneno,profileEmail,login12,ownername,ownernumber,owneraddress,shopgstno;
    EditText Fname,Lname,Pnumber,shopaddress;
    ImageView image1,image2;

    Uri imagedata;
    private static final int RequestCode=123;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile1);

        imageView=findViewById(R.id.imageview1);
        profilename=findViewById(R.id.profilename1);
        profilephoneno=findViewById(R.id.profilephoneno1);
        Fname=findViewById(R.id.fName1);
        Lname=findViewById(R.id.lName1);
        login12=findViewById(R.id.login122);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);

        owneraddress=findViewById(R.id.owneraddress);
        ownername=findViewById(R.id.ownername);
        ownernumber=findViewById(R.id.ownerphonenumber);
        shopaddress=findViewById(R.id.shopaddress);
        shopgstno=findViewById(R.id.shopgstno);


        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseStorage=FirebaseStorage.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating Your Profile");
        progressDialog.setCancelable(false);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"pick an image"),RequestCode);

            }
        });

        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Shopname=snapshot.child("Business Details").child("shopName").getValue().toString();
                String Shopnumber=snapshot.child("Business Details").child("shopNumber").getValue().toString();


                profilename.setText(Shopname);
                Fname.setText(Shopname);
                Lname.setText(Shopnumber);
                profilephoneno.setText(Shopnumber);

                Picasso.get().load(snapshot.child("Personal Details").child("image1").getValue().toString()).placeholder(R.drawable.avatar).into(image1);
                Picasso.get().load(snapshot.child("Personal Details").child("image2").getValue().toString()).placeholder(R.drawable.avatar).into(image2);

                owneraddress.setText(snapshot.child("Personal Details").child("address").getValue().toString());
                ownername.setText(snapshot.child("Personal Details").child("fullname").getValue().toString());
                ownernumber.setText(snapshot.child("Personal Details").child("phoneNumber").getValue().toString());

                shopaddress.setText(snapshot.child("Business Details").child("shopAddress").getValue().toString());
                shopgstno.setText(snapshot.child("Business Details").child("gstNumber").getValue().toString());


                Picasso.get().load((String) snapshot.child("Business Details").child("profilepic").getValue()).placeholder(R.drawable.avatar).into(imageView);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        login12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String firstName=Fname.getText().toString();
                final String Lastname=Lname.getText().toString();
                progressDialog.show();


                if (imagedata!=null){
                    final StorageReference storageReference=firebaseStorage.getReference().child("ProfilePic").child(firebaseAuth.getUid());
                    storageReference.putFile(imagedata).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String url=uri.toString();
                                        Service service=new Service(firstName,Lastname,"",shopaddress.getText().toString(),"1",url,firstName,shopgstno.getText().toString());
                                        //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("shopName").setValue(firstName);
                                        //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("shopNumber").setValue(Lastname);
                                        //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("shopAddress").setValue(shopaddress.getText().toString());
                                        //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("gstNumber").setValue(shopgstno.getText().toString());
                                        //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").child("fullname").setValue(ownername.getText().toString());
                                        //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").child("phoneNumber").setValue(ownernumber.getText().toString());
                                        //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").child("address").setValue(owneraddress.getText().toString());
                                        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").setValue(service).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                Intent i=new Intent(CompleteProfile1.this,PartnerMapActivity.class);
                                                startActivity(i);
                                                finish();
                                                progressDialog.dismiss();

                                            }
                                        });

                                       /* firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                String Image1=snapshot.child("image1").getValue().toString();
                                                String Image2=snapshot.child("image2").getValue().toString();
                                                String Dob=snapshot.child("dob").getValue().toString();
                                                String Email=snapshot.child("email").getValue().toString();
                                                String IdType=snapshot.child("idType").getValue().toString();
                                                String PartnerType=snapshot.child("partnerType").getValue().toString();
                                                String Password=snapshot.child("password").getValue().toString();
                                                String Type=snapshot.child("type").getValue().toString();

                                                Partner partner=new Partner(ownername.getText().toString(),ownernumber.getText().toString(),Email,Password,Type,owneraddress.getText().toString(),Dob,PartnerType,IdType,Image1,Image2);
                                                firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").setValue(partner).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });*/

                                    }
                                });
                            }
                        }
                    });
                }
                else{

                    firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String n=snapshot.child("Business Details").child("profilepic").getValue().toString();

                            Service service=new Service(firstName,Lastname,"",shopaddress.getText().toString(),"1",n,firstName,shopgstno.getText().toString());
                            firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").setValue(service).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Intent i=new Intent(CompleteProfile1.this,PartnerMapActivity.class);
                                    startActivity(i);
                                    finish();
                                    progressDialog.dismiss();

                                }
                            });

                            //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("shopName").setValue(firstName);
                            //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("shopNumber").setValue(Lastname);
                            //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("shopAddress").setValue(shopaddress.getText().toString());
                            //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("gstNumber").setValue(shopgstno.getText().toString());
                            //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").child("fullname").setValue(ownername.getText().toString());
                            //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").child("phoneNumber").setValue(ownernumber.getText().toString());
                            //firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").child("address").setValue(owneraddress.getText().toString());

                            /*String Image1=snapshot.child("Personal Details").child("image1").getValue().toString();
                            String Image2=snapshot.child("Personal Details").child("image2").getValue().toString();
                            String Dob=snapshot.child("Personal Details").child("dob").getValue().toString();
                            String Email=snapshot.child("Personal Details").child("email").getValue().toString();
                            String IdType=snapshot.child("Personal Details").child("idType").getValue().toString();
                            String PartnerType=snapshot.child("Personal Details").child("partnerType").getValue().toString();
                            String Password=snapshot.child("Personal Details").child("password").getValue().toString();
                            String Type=snapshot.child("Personal Details").child("type").getValue().toString();

                            Partner partner=new Partner(ownername.getText().toString(),ownernumber.getText().toString(),Email,Password,Type,owneraddress.getText().toString(),Dob,PartnerType,IdType,Image1,Image2);

                            firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Personal Details").setValue(partner).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                }
                            });*/

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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
            imagedata=data.getData();
            imageView.setImageURI(imagedata);
        }
    }
}