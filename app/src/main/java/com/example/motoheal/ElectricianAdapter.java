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

import java.util.ArrayList;

public class ElectricianAdapter extends RecyclerView.Adapter<ElectricianAdapter.ViewHolder> {

    Context context;
    ArrayList<Business> painters;
    String Service,Issues;

    public ElectricianAdapter(Context context, ArrayList<Business> painters,String Service,String Issues) {
        this.context = context;
        this.painters = painters;
        this.Service = Service;
        this.Issues = Issues;
    }

    @NonNull
    @Override
    public ElectricianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.garage_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElectricianAdapter.ViewHolder holder, int position) {

        final Business temp=painters.get(position);

        holder.name.setText(painters.get(position).getShopName());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,ElectricianInfo.class);
                i.putExtra("name",temp.getShopName());
                i.putExtra("owner",temp.getOwner());

                i.putExtra("Service",Service);
                i.putExtra("Issues",Issues);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return painters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.garagename);
            layout=itemView.findViewById(R.id.layout);
        }
    }
}
