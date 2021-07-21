package com.example.motoheal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.ViewHolder> {

    Context context;
    ArrayList<Request> user;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    private static final int REQUEST_CALL = 1;

    public CurrentAdapter(Context context, ArrayList<Request> user) {
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.service_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Request temp=user.get(position);
        holder.serviceid.setText(temp.getRequestId());
        //holder.sentto.setText(temp.getSentTo());
        holder.totalamount.setText(temp.getTotalAmount());
        holder.date.setText(temp.getDate());

        firebaseDatabase.getReference().child("Requests").child(temp.getBusinessName()).child(temp.getRequestId()).child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.sentfrom.setText(snapshot.child("custName").getValue().toString());
               holder.sentto.setText(snapshot.child("custNumber").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView serviceid,sentfrom,sentto,totalamount,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceid=itemView.findViewById(R.id.serviceid);
            sentfrom=itemView.findViewById(R.id.sentfrom);
            sentto=itemView.findViewById(R.id.sentto);
            totalamount=itemView.findViewById(R.id.totalamount);
            date=itemView.findViewById(R.id.date);
        }
    }

}
