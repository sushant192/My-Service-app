package com.example.motoheal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    Context context;
    ArrayList<Feedback> user;

    public FeedbackAdapter(Context context, ArrayList<Feedback> user) {
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cash_user_dialog,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.ViewHolder holder, int position) {

        final Feedback temp=user.get(position);
        holder.name.setText(temp.getName());
        holder.phoneNumber.setText(temp.getMessage());

    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,phoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.cashcustname);
            email=itemView.findViewById(R.id.amount);
            phoneNumber=itemView.findViewById(R.id.date1);
        }
    }
}
