package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentServices extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Request> user;

    CurrentAdapter currentAdapter;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_services);

        recyclerView=findViewById(R.id.recyclerView123);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        user=new ArrayList<>();

        currentAdapter=new CurrentAdapter(this,user);

        recyclerView.setAdapter(currentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    if (snapshot1.hasChild("Requests")){
                        for (DataSnapshot snapshot2:snapshot1.child("Requests").getChildren()){
                            Request user2=snapshot2.child("Info").getValue(Request.class);
                            if (user2.getFlag().contentEquals("1") && user2.getClickflag().contentEquals("") && user2.getBusinessName().contentEquals(firebaseAuth.getUid())){
                                user.add(user2);
                            }
                        }
                        currentAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}