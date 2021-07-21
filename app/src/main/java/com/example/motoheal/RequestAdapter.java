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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{

    Context context;
    ArrayList<Pending> cashPayments;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    public RequestAdapter(Context context, ArrayList<Pending> cashPayments) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Pending temp = cashPayments.get(position);

        holder.name.setText(temp.getShopName());
        holder.amount.setText(temp.getDate());
        holder.date.setText(temp.getPhoneNumber());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,AdminRequestDetails.class);
                i.putExtra("date",temp.getDate());
                i.putExtra("fullname",temp.getFullname());
                i.putExtra("phonenumber",temp.getPhoneNumber());
                i.putExtra("email",temp.getEmail());
                i.putExtra("address",temp.getAddress());
                i.putExtra("dob",temp.getDOB());
                i.putExtra("partnertype",temp.getPartnerType());
                i.putExtra("id",temp.getIdType());
                i.putExtra("image1",temp.getImage1());
                i.putExtra("image2",temp.getImage2());
                i.putExtra("shopname",temp.getShopName());
                i.putExtra("shopnumber",temp.getShopNumber());
                i.putExtra("shopaddress",temp.getShopAddress());
                i.putExtra("noofworkers",temp.getNoofworkers());
                i.putExtra("workshopname",temp.getWorkshopName());
                i.putExtra("gst",temp.getGst());
                i.putExtra("workername",temp.getWorkername());
                i.putExtra("workeraddress",temp.getWorkeraddress());
                i.putExtra("workerdob",temp.getWorkerdob());
                i.putExtra("workergender",temp.getWorkergender());
                i.putExtra("workerid",temp.getWorkeridtype());
                i.putExtra("workeridpic1",temp.getWorkeridpic1());
                i.putExtra("workeridpic2",temp.getWorkeridpic2());
                i.putExtra("password",temp.getPassword());
                i.putExtra("pendingid",temp.getPendingId());
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
