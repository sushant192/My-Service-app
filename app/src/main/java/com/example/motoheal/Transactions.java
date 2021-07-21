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
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity {

    RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    ArrayList<Transaction1> cashPayments;

    TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        recyclerView=findViewById(R.id.recyclerView123);
        bottomNavigationView=findViewById(R.id.bottom);
        bottomNavigationView.setSelectedItemId(R.id.cash);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        cashPayments=new ArrayList<>();

        transactionAdapter=new TransactionAdapter(this,cashPayments);

        recyclerView.setAdapter(transactionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.credit:
                        Intent i=new Intent(Transactions.this,OnlineTransactions.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Transaction Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cashPayments.clear();
                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){

                    Transaction1 user1=snapshot1.getValue(Transaction1.class);
                    if (user1.getMode().contentEquals("Cash")){
                        cashPayments.add(user1);
                    }
                }
                transactionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}