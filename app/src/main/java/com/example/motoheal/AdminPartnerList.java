package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminPartnerList extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminGarageAdapter adminAdapter;
    ArrayList<Partner> user;

    BottomNavigationView bottomNavigationView;

    FirebaseAuth auth;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_partner_list);

        recyclerView=findViewById(R.id.adminrecycler);
        user=new ArrayList<>();
        adminAdapter=new AdminGarageAdapter(this,user);

        bottomNavigationView=findViewById(R.id.bottom3);
        bottomNavigationView.setSelectedItemId(R.id.garage);

        auth=FirebaseAuth.getInstance();
        user1=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.plumber1:
                        Intent i=new Intent(AdminPartnerList.this,AdminPlumberList.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.electrician1:
                        Intent j=new Intent(AdminPartnerList.this,AdminElectricianList.class);
                        startActivity(j);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.painter1:
                        Intent k=new Intent(AdminPartnerList.this,AdminPainterList.class);
                        startActivity(k);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });


        firebaseDatabase.getReference().child("Partners").child("partners").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Partner user1=snapshot1.child("Personal Details").getValue(Partner.class);
                    if (user1.getPartnerType().contentEquals("Garage Owner")){
                        user.add(user1);
                    }
                }
                adminAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}