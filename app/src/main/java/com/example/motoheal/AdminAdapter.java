package com.example.motoheal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    Context context;
    ArrayList<User> user;

    public AdminAdapter(Context context, ArrayList<User> user) {
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cash_user_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User temp=user.get(position);

        String fname=temp.getFirstName().toString();
        String lname=temp.getLastName().toString();
        String fullname=fname+lname;

        holder.name.setText(fullname);
        holder.phoneNumber.setText(user.get(position).getPhoneNumber());
        holder.email.setText(user.get(position).getEmail());

        Picasso.get().load(temp.getProfilePic()).placeholder(R.drawable.avatar).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,phoneNumber;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.cashcustname);
            email=itemView.findViewById(R.id.amount);
            phoneNumber=itemView.findViewById(R.id.date1);
            imageView=itemView.findViewById(R.id.image50);
        }
    }
}
