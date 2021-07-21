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

public class NearbyPainters extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    ArrayList<Business> user;
    PainterAdapter painterAdapter;
    Spinner spinner3;
    String partnerType2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_painters);

        recyclerView=findViewById(R.id.recycler1);
        spinner3=findViewById(R.id.spinner3);

        user=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();

        String TotalRooms=getIntent().getStringExtra("TotalRooms");
        String HowmanyDays=getIntent().getStringExtra("HowmanyDays");
        String imagedata=getIntent().getStringExtra("imagedata");
        String imagedata1=getIntent().getStringExtra("imagedata1");
        String imagedata2=getIntent().getStringExtra("imagedata2");
        String imagedata3=getIntent().getStringExtra("imagedata3");
        String imagedata4=getIntent().getStringExtra("imagedata4");
        String imagedata5=getIntent().getStringExtra("imagedata5");

        painterAdapter=new PainterAdapter(NearbyPainters.this,user,TotalRooms,HowmanyDays,imagedata,imagedata1,imagedata2,imagedata3,imagedata4,imagedata5);

        recyclerView.setAdapter(painterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NearbyPainters.this));

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



        firebaseDatabase.getReference().child("Partners").child("Business").child("Painting business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Business user1=snapshot1.getValue(Business.class);
                    user.add(user1);
                }

                painterAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}