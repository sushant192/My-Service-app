package com.example.motoheal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class WithdrawAdapter extends RecyclerView.Adapter<WithdrawAdapter.ViewHolder> {

    Context context;
    ArrayList<Withdraw> cashPayments;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    public WithdrawAdapter(Context context, ArrayList<Withdraw> cashPayments) {
        this.context = context;
        this.cashPayments = cashPayments;
    }

    @NonNull
    @Override
    public WithdrawAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.cash_user_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Withdraw temp = cashPayments.get(position);

        holder.name.setText(temp.getBusinessName());
        holder.amount.setText(temp.getAmount());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String s=temp.getWithdrawId();
                final String sk=temp.getBusinessId();

                firebaseDatabase.getReference().child("Withdraw Requests").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            String WithdrawId=snapshot1.child("withdrawId").getValue().toString();
                            String BusinessId=snapshot1.child("businessId").getValue().toString();

                            if (WithdrawId.contentEquals(s) && BusinessId.contentEquals(sk)){
                                firebaseDatabase.getReference().child("Withdraw Requests").child(s).child("flag").setValue("1");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent i=new Intent(context,DisplayPaymentInfo1.class);
                i.putExtra("Receiver",temp.getBusinessName().toString());
                i.putExtra("Amount",temp.getAmount());
                i.putExtra("UpiId",temp.getUpiId());
                i.putExtra("UpiName",temp.getUpiName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cashPayments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,date,amount;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.cashcustname);
            amount=itemView.findViewById(R.id.amount);
            date=itemView.findViewById(R.id.date1);
            layout=itemView.findViewById(R.id.lay);
        }
    }
}
