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

public class NearbyElectricians1 extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    ArrayList<Business> user;
    ElectricianAdapter1 electricianAdapter;
    Spinner spinner3;
    String partnerType2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_electricians1);

        recyclerView=findViewById(R.id.recycler45);
        spinner3=findViewById(R.id.spinner3);

        user=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();

        String TotalRooms=getIntent().getStringExtra("TotalRooms");
        String HowmanyDays=getIntent().getStringExtra("HowmanyDays");
        String imagedata1=getIntent().getStringExtra("imagedata1");
        String imagedata2=getIntent().getStringExtra("imagedata2");
        String imagedata3=getIntent().getStringExtra("imagedata3");


        electricianAdapter=new ElectricianAdapter1(this,user,TotalRooms,HowmanyDays,imagedata1,imagedata2,imagedata3);

        recyclerView.setAdapter(electricianAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        firebaseDatabase.getReference().child("Partners").child("Business").child("Electrician business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Business user1=snapshot1.getValue(Business.class);
                    user.add(user1);
                }
                electricianAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}