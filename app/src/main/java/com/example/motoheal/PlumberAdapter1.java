package com.example.motoheal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlumberAdapter1 extends RecyclerView.Adapter<PlumberAdapter1.ViewHolder> {

    Context context;
    ArrayList<Business> painters;
    ArrayList<Uri> mlist;
    String TotalRooms,HowmanyDays,imagedata1,imagedata2,imagedata3;

    public PlumberAdapter1(Context context, ArrayList<Business> painters,String TotalRooms,String HowmanyDays,String imagedata1,String imagedata2,String  imagedata3) {
        this.context = context;
        this.painters = painters;
        this.TotalRooms = TotalRooms;
        this.HowmanyDays = HowmanyDays;
        this.imagedata1 = imagedata1;
        this.imagedata2 = imagedata2;
        this.imagedata3 = imagedata3;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.garage_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Business temp=painters.get(position);

        holder.name.setText(painters.get(position).getShopName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,PlumberInfo1.class);
                i.putExtra("name",temp.getShopName());
                i.putExtra("owner",temp.getOwner());

                i.putExtra("TotalRooms",TotalRooms);
                i.putExtra("HowmanyDays",HowmanyDays);
                i.putExtra("imagedata1",imagedata1);
                i.putExtra("imagedata2",imagedata2);
                i.putExtra("imagedata3",imagedata3);
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
