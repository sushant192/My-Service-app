package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CoWorkers extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView addWorker;

    FirebaseDatabase firebaseDatabase;
    ArrayList<Worker> worker;
    WorkerAdapter workerAdapter;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_workers);

        recyclerView=findViewById(R.id.recycler123);
        addWorker=findViewById(R.id.addworker);

        worker=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        workerAdapter=new WorkerAdapter(this,worker);

        recyclerView.setAdapter(workerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CoWorkers.this,WorkerDetails1.class);
                startActivity(i);
                finish();
            }
        });

        firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Worker Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                worker.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Worker worker1=snapshot1.getValue(Worker.class);
                    worker.add(worker1);
                }
                workerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}