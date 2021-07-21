package com.example.motoheal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.ViewHolder> {

    Context context;
    ArrayList<User1> user1;
    RecyclerView rec;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();


    public DialogAdapter(Context context, ArrayList<User1> user1) {
        this.context = context;
        this.user1 = user1;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        rec=recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cash_user_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final User1 temp=user1.get(position);

        holder.name.setText(user1.get(position).getCustName());
        holder.number.setText(user1.get(position).getCustNumber());
        holder.date.setText(temp.getDate());
        Picasso.get().load(temp.getCustpic()).placeholder(R.drawable.avatar).into(holder.image);

        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.accept_dialog);
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

                if (temp.getType().contentEquals("Garage")){
                    Intent i=new Intent(context,RequestDetails.class);
                    i.putExtra("name",temp.getCustName());
                    i.putExtra("phone",temp.getCustNumber());
                    i.putExtra("pic",temp.getCustpic());
                    i.putExtra("id",temp.getCustId());
                    i.putExtra("requestId",temp.getCustRequestId());
                    i.putExtra("date",temp.getDate());
                    context.startActivity(i);
                }
                if (temp.getType().contentEquals("Plumber1")){

                    Intent i=new Intent(context,RequestDetails1.class);
                    i.putExtra("name",temp.getCustName());
                    i.putExtra("phone",temp.getCustNumber());
                    i.putExtra("pic",temp.getCustpic());
                    i.putExtra("id",temp.getCustId());
                    i.putExtra("requestId",temp.getCustRequestId());
                    i.putExtra("date",temp.getDate());
                    context.startActivity(i);

                }
                if (temp.getType().contentEquals("Plumber2")){
                    Intent i=new Intent(context,RequestDetails2.class);
                    i.putExtra("name",temp.getCustName());
                    i.putExtra("phone",temp.getCustNumber());
                    i.putExtra("pic",temp.getCustpic());
                    i.putExtra("id",temp.getCustId());
                    i.putExtra("requestId",temp.getCustRequestId());
                    i.putExtra("date",temp.getDate());
                    context.startActivity(i);
                }
                if (temp.getType().contentEquals("Electrician1")){
                    Intent i=new Intent(context,RequestDetails3.class);
                    i.putExtra("name",temp.getCustName());
                    i.putExtra("phone",temp.getCustNumber());
                    i.putExtra("pic",temp.getCustpic());
                    i.putExtra("id",temp.getCustId());
                    i.putExtra("requestId",temp.getCustRequestId());
                    i.putExtra("date",temp.getDate());
                    context.startActivity(i);
                }
                if (temp.getType().contentEquals("Electrician2")){

                    Intent i=new Intent(context,RequestDetails4.class);
                    i.putExtra("name",temp.getCustName());
                    i.putExtra("phone",temp.getCustNumber());
                    i.putExtra("pic",temp.getCustpic());
                    i.putExtra("id",temp.getCustId());
                    i.putExtra("requestId",temp.getCustRequestId());
                    i.putExtra("date",temp.getDate());
                    context.startActivity(i);

                }
                if (temp.getType().contentEquals("Painter")){
                    Intent i=new Intent(context,RequestDetails5.class);
                    i.putExtra("name",temp.getCustName());
                    i.putExtra("phone",temp.getCustNumber());
                    i.putExtra("pic",temp.getCustpic());
                    i.putExtra("id",temp.getCustId());
                    i.putExtra("requestId",temp.getCustRequestId());
                    i.putExtra("date",temp.getDate());
                    context.startActivity(i);

                }

            }
        });


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=temp.getCustName();
                final String id=temp.getCustId();
                final String Requestid=temp.getCustRequestId();
                final String pic=temp.getCustpic();
                final String no=temp.getCustNumber();
                dialog.show();

                nameDialog.setText(fname);
                phoneno.setText(no);
                Picasso.get().load(pic).placeholder(R.drawable.avatar).into(pro);

            }
        });
    }

    @Override
    public int getItemCount() {
        return user1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView name,number,date;
        final LinearLayout layout;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cashcustname);
            number=itemView.findViewById(R.id.amount);
            date=itemView.findViewById(R.id.date1);
            layout=itemView.findViewById(R.id.lay);
            image=itemView.findViewById(R.id.image50);
        }
    }
}
