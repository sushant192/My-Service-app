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

public class AdminUserList extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminAdapter adminAdapter;
    ArrayList<User> user;

    FirebaseAuth auth;
    FirebaseUser user1;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);

        recyclerView=findViewById(R.id.adminrecycle1);
        user=new ArrayList<>();
        adminAdapter=new AdminAdapter(this,user);

        auth=FirebaseAuth.getInstance();
        user1=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();

        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    User user1=snapshot1.child("Personal details").getValue(User.class);
                    user.add(user1);
                }
                adminAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}