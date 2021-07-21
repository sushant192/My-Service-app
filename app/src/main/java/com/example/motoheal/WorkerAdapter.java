package com.example.motoheal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    Context context;
    ArrayList<Worker> workers;

    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user1=auth.getCurrentUser();
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

    public WorkerAdapter(Context context, ArrayList<Worker> workers) {
        this.context = context;
        this.workers = workers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.customer_dialog1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Worker temp=workers.get(position);
        holder.name.setText(workers.get(position).getWorkername());
        holder.address.setText(temp.getWorkeraddress());
        holder.number.setText(temp.getWorkerdob());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,WorkerProfile.class);
                i.putExtra("address",temp.getWorkeraddress());
                i.putExtra("dob",temp.getWorkerdob());
                i.putExtra("fullname",temp.getWorkername());
                i.putExtra("phonenumber",temp.getWorkerphone());
                i.putExtra("id",temp.getWorkerid());
                context.startActivity(i);

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Worker Details").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            if (snapshot1.child("workername").getValue().toString().contentEquals(temp.getWorkername())){
                                firebaseDatabase.getReference().child("Partners").child("partners").child(auth.getUid()).child("Worker Details").child(snapshot1.getKey()).removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,WorkerDetails3.class);
                i.putExtra("address",temp.getWorkeraddress());
                i.putExtra("dob",temp.getWorkerdob());
                i.putExtra("fullname",temp.getWorkername());
                i.putExtra("phonenumber",temp.getWorkerphone());
                i.putExtra("id",temp.getWorkerid());
                i.putExtra("image1",temp.getImage1());
                i.putExtra("image2",temp.getImage2());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,address,number;
        LinearLayout layout;
        Button delete,edit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.custname);
            address=itemView.findViewById(R.id.custaddress);
            number=itemView.findViewById(R.id.custnumber);
            layout=itemView.findViewById(R.id.layout1);
            delete=itemView.findViewById(R.id.delete);
            edit=itemView.findViewById(R.id.edit);
        }
    }
}
