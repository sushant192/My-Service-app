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

public class OnlineTransactions1 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView1;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    ArrayList<Transaction> cashPayments1;

    TransactionAdapter1 transactionAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_transactions1);

        bottomNavigationView=findViewById(R.id.bottom);
        bottomNavigationView.setSelectedItemId(R.id.credit);
        recyclerView1=findViewById(R.id.recyclerView1234);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        cashPayments1=new ArrayList<>();

        transactionAdapter1=new TransactionAdapter1(this,cashPayments1);

        recyclerView1.setAdapter(transactionAdapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.cash:
                        Intent i=new Intent(OnlineTransactions1.this,UserTransactions.class);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        firebaseDatabase.getReference().child("Users").child(auth.getUid()).child("Payments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cashPayments1.clear();
                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    Transaction user1=snapshot1.getValue(Transaction.class);
                    cashPayments1.add(user1);
                }
                transactionAdapter1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}