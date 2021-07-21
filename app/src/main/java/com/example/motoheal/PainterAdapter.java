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

public class PainterAdapter extends RecyclerView.Adapter<PainterAdapter.ViewHolder> {

    Context context;
    ArrayList<Business> painters;
    String TotalRooms,HowmanyDays,imagedata,imagedata1,imagedata2,imagedata3,imagedata4,imagedata5;

    public PainterAdapter(Context context, ArrayList<Business> painters,String TotalRooms,String HowmanyDays,String imagedata,String imagedata1,String imagedata2,String imagedata3,String imagedata4,String imagedata5) {
        this.context = context;
        this.painters = painters;
        this.TotalRooms = TotalRooms;
        this.HowmanyDays = HowmanyDays;
        this.imagedata = imagedata;
        this.imagedata1 = imagedata1;
        this.imagedata2 = imagedata2;
        this.imagedata3 = imagedata3;
        this.imagedata4 = imagedata4;
        this.imagedata5 = imagedata5;
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
                Intent i=new Intent(context,PainterInfo.class);
                i.putExtra("name",temp.getShopName());
                i.putExtra("owner",temp.getOwner());

                i.putExtra("TotalRooms",TotalRooms);
                i.putExtra("HowmanyDays",HowmanyDays);
                i.putExtra("imagedata",imagedata);
                i.putExtra("imagedata1",imagedata1);
                i.putExtra("imagedata2",imagedata2);
                i.putExtra("imagedata3",imagedata3);
                i.putExtra("imagedata4",imagedata4);
                i.putExtra("imagedata5",imagedata5);
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
