package com.example.motoheal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GarageAdapter extends RecyclerView.Adapter<GarageAdapter.ViewHolder> {
    Context context;
    ArrayList<Business> garages;
    String VehicleType,VehicleCompany,VehicleModel,digitalMeterRepair,clutch,break1,accelerator,ac,lock,servicing,engine,puncture,dentingpainting,lights,battery,washing,issues;

    public GarageAdapter(Context context, ArrayList<Business> garages,String VehicleType,String VehicleCompany,String VehicleModel,String digitalMeterRepair,String clutch,String break1,String accelerator,String ac,String lock,String servicing,String engine,String puncture,String dentingpainting,String lights,String battery,String washing,String issues) {
        this.context = context;
        this.garages = garages;
        this.VehicleType = VehicleType;
        this.VehicleCompany = VehicleCompany;
        this.VehicleModel = VehicleModel;
        this.digitalMeterRepair = digitalMeterRepair;
        this.clutch = clutch;
        this.break1 = break1;
        this.accelerator = accelerator;
        this.ac = ac;
        this.lock = lock;
        this.servicing = servicing;
        this.engine = engine;
        this.puncture = puncture;
        this.dentingpainting = dentingpainting;
        this.lights = lights;
        this.battery = battery;
        this.washing = washing;
        this.issues = issues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.garage_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Business temp=garages.get(position);

        holder.name.setText(garages.get(position).getShopName());
        //holder.name.setText(users.get(position).getName());
        //holder.name.setText(users.get(position).getName());
        //Glide.with(holder.itemView.getContext()).load(users.get(position).getImage()).placeholder(R.drawable.avatar).into(holder.imageView);


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,GarageInfo.class);
                i.putExtra("name",temp.getShopName());
                i.putExtra("owner",temp.getOwner());

                i.putExtra("VehicleType",VehicleType);
                i.putExtra("VehicleCompany",VehicleCompany);
                i.putExtra("VehicleModel",VehicleModel);
                i.putExtra("digitalMeterRepair",digitalMeterRepair);
                i.putExtra("clutch",clutch);
                i.putExtra("break",break1);
                i.putExtra("accelerator",accelerator);
                i.putExtra("ac",ac);
                i.putExtra("lock",lock);
                i.putExtra("servicing",servicing);
                i.putExtra("engine",engine);
                i.putExtra("puncture",puncture);
                i.putExtra("dentingpainting",dentingpainting);
                i.putExtra("lights",lights);
                i.putExtra("battery",battery);
                i.putExtra("washing",washing);
                i.putExtra("issues",issues);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return garages.size();
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
