package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RequestDetails extends AppCompatActivity {

    ImageView imageView;
    Button accept,callnow;
    TextView custname,date,custnumber,vehicletype,vehiclecompany,vehiclemodel,issues,ac,accelerator,battery,break1,clutch,denting,digitalmeter,engine,lights,lock,puncture,servicing,washing,totalamount,gst,travelfair,total;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        imageView=findViewById(R.id.profilepic);
        custname=findViewById(R.id.ownername1);
        date=findViewById(R.id.date);
        custnumber=findViewById(R.id.number1);
        vehicletype=findViewById(R.id.vehicletype);
        vehiclecompany=findViewById(R.id.vehiclecompany);
        vehiclemodel=findViewById(R.id.vehiclemodel);
        issues=findViewById(R.id.issues7);
        washing=findViewById(R.id.washing1);
        totalamount=findViewById(R.id.totalamount);
        gst=findViewById(R.id.gst);
        travelfair=findViewById(R.id.travelfair);
        total=findViewById(R.id.total);
        callnow=findViewById(R.id.callnow);

        ac=findViewById(R.id.ac);
        accelerator=findViewById(R.id.accelerator1);
        battery=findViewById(R.id.battery);
        break1=findViewById(R.id.break2);
        clutch=findViewById(R.id.clutch);
        denting=findViewById(R.id.dentingpainting1);
        digitalmeter=findViewById(R.id.digitalmeterRepair1);
        engine=findViewById(R.id.engine1);
        lights=findViewById(R.id.lights1);
        lock=findViewById(R.id.lock1);
        puncture=findViewById(R.id.puncture1);
        servicing=findViewById(R.id.servicing1);
        accept=findViewById(R.id.paycash);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        String name =getIntent().getStringExtra("name");
        String phone =getIntent().getStringExtra("phone");
        String pic =getIntent().getStringExtra("pic");
        final String id =getIntent().getStringExtra("id");
        final String requestId=getIntent().getStringExtra("requestId");
        final String Date=getIntent().getStringExtra("date");

        custname.setText(name);
        custnumber.setText(phone);
        date.setText(Date);
        Picasso.get().load(pic).placeholder(R.drawable.avatar).into(imageView);

        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        firebaseDatabase.getReference().child("Users").child(id).child("Requests").child(requestId).child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Ac=snapshot.child("ac").getValue().toString();
                if (Ac.contentEquals("")){
                    ac.setText("-");
                }
                else{
                    ac.setText("Yes");
                }

                String Accelerator=snapshot.child("accelerator").getValue().toString();
                if (Accelerator.contentEquals("")){
                    accelerator.setText("-");
                }
                else{
                    accelerator.setText("Yes");
                }

                String Battery=snapshot.child("battery").getValue().toString();
                if (Battery.contentEquals("")){
                    battery.setText("-");
                }
                else{
                    battery.setText("Yes");
                }

                String Break=snapshot.child("break1").getValue().toString();
                if (Break.contentEquals("")){
                    break1.setText("-");
                }
                else{
                    break1.setText("Yes");
                }

                String Clutch=snapshot.child("clutch").getValue().toString();
                if (Clutch.contentEquals("")){
                    clutch.setText("-");
                }
                else{
                    clutch.setText("Yes");
                }

                String Denting=snapshot.child("dentingpainting").getValue().toString();
                if (Denting.contentEquals("")){
                    denting.setText("-");
                }
                else{
                    denting.setText("Yes");
                }

                String Digital=snapshot.child("digitalMeterRepair").getValue().toString();
                if (Digital.contentEquals("")){
                    digitalmeter.setText("-");
                }
                else{
                    digitalmeter.setText("Yes");
                }

                String Engine=snapshot.child("engine").getValue().toString();
                if (Engine.contentEquals("")){
                    engine.setText("-");
                }
                else{
                    engine.setText("Yes");
                }

                String Lights=snapshot.child("lights").getValue().toString();
                if (Lights.contentEquals("")){
                    lights.setText("-");
                }
                else{
                    lights.setText("Yes");
                }

                String Lock=snapshot.child("lock").getValue().toString();
                if (Lock.contentEquals("")){
                    lock.setText("-");
                }
                else{
                    lock.setText("Yes");
                }

                String Puncture=snapshot.child("puncture").getValue().toString();
                if (Puncture.contentEquals("")){
                    puncture.setText("-");
                }
                else{
                    puncture.setText("Yes");
                }

                String Servicing=snapshot.child("servicing").getValue().toString();
                if (Servicing.contentEquals("")){
                    servicing.setText("-");
                }
                else{
                    servicing.setText("Yes");
                }

                String Washing=snapshot.child("washing").getValue().toString();
                if (Washing.contentEquals("")){
                    washing.setText("-");
                }
                else{
                    washing.setText("Yes");
                }

                String VehicleType=snapshot.child("vehicleType").getValue().toString();
                vehicletype.setText(VehicleType);

                String VehicleCompany=snapshot.child("vehicleCompany").getValue().toString();
                vehiclecompany.setText(VehicleCompany);

                String VehicleModel=snapshot.child("vehicleModel").getValue().toString();
                vehiclemodel.setText(VehicleModel);

                String Issues=snapshot.child("issues").getValue().toString();
                issues.setText(Issues);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReference().child("Users").child(id).child("Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    String s=snapshot1.child("Info").child("requestId").getValue().toString();
                    //String s1=snapshot1.child("custId").getValue().toString();

                    if (snapshot1.getKey().contentEquals(requestId)  && snapshot1.child("Info").child("flag").getValue().toString().contentEquals("1")){

                        accept.setVisibility(View.INVISIBLE);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReference().child("Prices").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Integer> arr=new ArrayList<>();
                int Sum=0;

                if (ac.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("AC").getValue().toString());
                    arr.add(s);
                }
                if (accelerator.getText().toString().contentEquals("Yes")){
                    int s1=Integer.parseInt(snapshot.child("Accelerator").getValue().toString());
                    arr.add(s1);
                }
                if (battery.getText().toString().contentEquals("Yes")){
                    int s2=Integer.parseInt(snapshot.child("Battery").getValue().toString());
                    arr.add(s2);
                }
                if (break1.getText().toString().contentEquals("Yes")){
                    int s3=Integer.parseInt(snapshot.child("Break").getValue().toString());
                    arr.add(s3);
                }
                if (clutch.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Clutch").getValue().toString());
                    arr.add(s);
                }
                if (denting.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Denting and Painting").getValue().toString());
                    arr.add(s);
                }
                if (digitalmeter.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Digital Meter Repair").getValue().toString());
                    arr.add(s);
                }
                if (engine.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Engine").getValue().toString());
                    arr.add(s);
                }
                if (lights.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Lights").getValue().toString());
                    arr.add(s);
                }
                if (lock.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Lock").getValue().toString());
                    arr.add(s);
                }
                if (puncture.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Puncture").getValue().toString());
                    arr.add(s);
                }
                if (servicing.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Periodic Servicing").getValue().toString());
                    arr.add(s);
                }
                if (washing.getText().toString().contentEquals("Yes")){
                    int s=Integer.parseInt(snapshot.child("Washing").getValue().toString());
                    arr.add(s);
                }

                for (int i=0;i<arr.size();i++){
                    Sum=Sum+ arr.get(i);
                }


                totalamount.setText(String.valueOf(Sum));

                int h= (int) (Sum*0.18);

                gst.setText(String.valueOf(h));
                total.setText(String.valueOf(Sum+h));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase.getReference().child("Requests").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            String s=snapshot1.child("Info").child("flag").getValue().toString();
                            String s1=snapshot1.child("Info").child("custId").getValue().toString();
                            String s2=snapshot1.getKey();

                            if (s.contentEquals("")&& s1.contentEquals(id) && s2.contentEquals(requestId)){
                                //firebaseDatabase.getReference().child("Requests").child(firebaseAuth.getUid()).child(snapshot1.getKey()).child("Info").child("flag").setValue("1");
                                firebaseDatabase.getReference().child("Requests").child(firebaseAuth.getUid()).child(snapshot1.getKey()).child("Info").child("totalAmount").setValue(totalamount.getText().toString());

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                firebaseDatabase.getReference().child("Users").child(id).child("Requests").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            String s=snapshot1.child("Info").child("requestId").getValue().toString();
                            //String s1=snapshot1.child("custId").getValue().toString();

                            if (snapshot1.getKey().contentEquals(requestId)){
                                firebaseDatabase.getReference().child("Users").child(id).child("Requests").child(snapshot1.getKey()).child("Info").child("flag").setValue("1");
                                firebaseDatabase.getReference().child("Users").child(id).child("Requests").child(snapshot1.getKey()).child("Info").child("totalAmount").setValue(totalamount.getText().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                accept.setVisibility(View.INVISIBLE);


                Intent i=new Intent(RequestDetails.this,PartnerMapActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void makePhoneCall() {
        String number = custnumber.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(RequestDetails.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(RequestDetails.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(RequestDetails.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}