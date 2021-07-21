package com.example.motoheal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DialogAdapter1 extends RecyclerView.Adapter<DialogAdapter1.ViewHolder> {

    Context context;
    ArrayList<Request> user1;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    //FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    public DialogAdapter1(Context context, ArrayList<Request> user1) {
        this.context = context;
        this.user1 = user1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.customer_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Request temp=user1.get(position);
        holder.name.setText(user1.get(position).getSentTo());
        holder.number.setText(temp.getDate());

        firebaseDatabase.getReference().child("Partners").child("partners").child(temp.getBusinessName()).child("Business Details").child("shopAddress").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.address.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.accept_dialog1);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);



        final TextView nameDialog=dialog.findViewById(R.id.namedialog);
        final TextView phoneno=dialog.findViewById(R.id.phoneno);
        final Button confirm=dialog.findViewById(R.id.confirm);
        final Button more=dialog.findViewById(R.id.more);
        final ImageView pro=dialog.findViewById(R.id.pro1);
        final ImageView cancel=dialog.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,DisplayPaymentInfo.class);
                i.putExtra("ownername",temp.getSentTo());
                i.putExtra("ownerId",temp.getBusinessName());
                i.putExtra("totalamount",temp.getTotalAmount());
                i.putExtra("timetaken",temp.getTimetaken());
                i.putExtra("date",temp.getDate());
                i.putExtra("n",temp.getRequestId());
                i.putExtra("n1",temp.getBusinessName());
                context.startActivity(i);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n=temp.getRequestId();
                final String n1=temp.getBusinessName();
                final String n2=temp.getSentTo();
                final String n3=temp.getDate();
                dialog.show();

                nameDialog.setText(n2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView name,number,address;
        final LinearLayout layout;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.custname);
            number=itemView.findViewById(R.id.custnumber);
            layout=itemView.findViewById(R.id.layout1);
            image=itemView.findViewById(R.id.profile);
            address=itemView.findViewById(R.id.custaddress);
        }
    }
}
