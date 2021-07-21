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

public class AdminRequests extends AppCompatActivity {

    RecyclerView recrequest;
    ArrayList<Pending> user1;
    RequestAdapter dialogAdapter;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_requests);

        recrequest=findViewById(R.id.recrequests);

        user1=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        dialogAdapter=new RequestAdapter(this,user1);

        recrequest.setAdapter(dialogAdapter);
        recrequest.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase.getReference().child("Pending Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Pending user2=snapshot1.getValue(Pending.class);
                    if (user2.getPendingId().contentEquals(snapshot1.getKey()) && user2.getStatus().contentEquals("")){
                        user1.add(user2);
                    }
                }
                dialogAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}