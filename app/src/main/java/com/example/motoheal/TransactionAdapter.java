package com.example.motoheal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

        Context context;
        ArrayList<Transaction1> cashPayments;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        public TransactionAdapter(Context context, ArrayList<Transaction1> cashPayments) {
          this.context = context;
          this.cashPayments = cashPayments;
        }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cash_user_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Transaction1 temp = cashPayments.get(position);

        firebaseDatabase.getReference().child("Users").child(cashPayments.get(position).getSentFrom()).child("Personal details").child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstname = snapshot.getValue().toString();
                holder.name.setText(firstname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.amount.setText(temp.getTotalPrice());
        holder.date.setText(temp.getDate());
    }

    @Override
    public int getItemCount() { return cashPayments.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

            TextView name,date,amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.cashcustname);
            amount=itemView.findViewById(R.id.amount);
            date=itemView.findViewById(R.id.date1);
        }
    }
}
