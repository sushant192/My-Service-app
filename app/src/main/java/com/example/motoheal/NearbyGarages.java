package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NearbyGarages extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    ArrayList<Business> user;
    GarageAdapter garageAdapter;
    Spinner spinner3;
    String partnerType2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_garages);

        recyclerView=findViewById(R.id.recycler);

        user=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        spinner3=findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.km, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter2);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partnerType2=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        String VehicleType=getIntent().getStringExtra("VehicleType");
        String VehicleCompany=getIntent().getStringExtra("VehicleCompany");
        String VehicleModel=getIntent().getStringExtra("VehicleModel");
        String digitalMeterRepair=getIntent().getStringExtra("digitalMeterRepair");
        String clutch=getIntent().getStringExtra("clutch");
        String break1=getIntent().getStringExtra("break");
        String accelerator=getIntent().getStringExtra("accelerator");
        String ac=getIntent().getStringExtra("ac");
        String lock=getIntent().getStringExtra("lock");
        String servicing=getIntent().getStringExtra("servicing");
        String engine=getIntent().getStringExtra("engine");
        String puncture=getIntent().getStringExtra("puncture");
        String dentingpainting=getIntent().getStringExtra("dentingpainting");
        String lights=getIntent().getStringExtra("lights");
        String battery=getIntent().getStringExtra("battery");
        String washing=getIntent().getStringExtra("washing");
        String issues=getIntent().getStringExtra("issues");

        garageAdapter=new GarageAdapter(this,user,VehicleType,VehicleCompany,VehicleModel,digitalMeterRepair,clutch,break1,accelerator,ac,lock,servicing,engine,puncture,dentingpainting,lights,battery,washing,issues);

        recyclerView.setAdapter(garageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase.getReference().child("Partners").child("Business").child("Garage Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Business user1=snapshot1.getValue(Business.class);
                    user.add(user1);
                }
                garageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}