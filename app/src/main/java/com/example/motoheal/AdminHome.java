package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {

    BarChart barChart;

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    SearchView searchView;
    Toolbar toolbar;
    TextView services, submit;
    EditText type, company, model, service;
    //AlertDialog.Builder alertdialog;
    //AlertDialog dialog;
    Dialog dialog;
    String name,pho;

    RecyclerView rec,recyclerView;
    ArrayList<Withdraw> user1;
    ArrayList<Request> user;
    WithdrawAdapter dialogAdapter;
    CurrentAdapter currentAdapter;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        navigationView = findViewById(R.id.navi);
        drawerLayout = findViewById(R.id.drawlayout);
        toolbar = findViewById(R.id.toolbar1);
        searchView = findViewById(R.id.search);
        services = (TextView) findViewById(R.id.services);
        //spinner=findViewById(R.id.spinner);
        //button=findViewById(R.id.button);
        rec=findViewById(R.id.withdrawrec);
        recyclerView=findViewById(R.id.currentservice);
        user1=new ArrayList<>();
        user=new ArrayList<>();

        barChart=findViewById(R.id.barchart);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        dialogAdapter=new WithdrawAdapter(this,user1);
        currentAdapter=new CurrentAdapter(this,user);

        rec.setAdapter(dialogAdapter);
        rec.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(currentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View layout=navigationView.getHeaderView(0);
        final TextView lay=layout.findViewById(R.id.name2);
        final TextView lay1=layout.findViewById(R.id.phone);

        lay.setText("Welcome Admin");
        lay1.setText("Moto Heal");

        firebaseDatabase.getReference().child("Admin").child("Notifications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.child("Registration Requests").getChildren()){

                    String s=snapshot1.child("flag").getValue().toString();

                    if (s.contentEquals("")){
                        String Name=snapshot1.child("name").getValue().toString();
                        String Body=snapshot1.child("body").getValue().toString();

                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                            NotificationChannel channel=new NotificationChannel("My notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager manager=getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(channel);
                        }

                        NotificationCompat.Builder builder=new NotificationCompat.Builder(AdminHome.this,"My notification").setSmallIcon(R.drawable.ic_baseline_message_24).setContentTitle(Name).setContentText(Body).setAutoCancel(true);
                        Intent i=new Intent(AdminHome.this,AdminRequests.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        PendingIntent pendingIntent=PendingIntent.getActivity(AdminHome.this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);

                        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(AdminHome.this);
                        notificationManagerCompat.notify(1,builder.build());

                        firebaseDatabase.getReference().child("Admin").child("Notifications").child("Registration Requests").child(snapshot1.getKey()).child("flag").setValue("1");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(1,200));
        barEntries.add(new BarEntry(2,250));
        barEntries.add(new BarEntry(3,100));
        barEntries.add(new BarEntry(4,600));
        barEntries.add(new BarEntry(5,350));
        barEntries.add(new BarEntry(6,280));
        barEntries.add(new BarEntry(7,400));
        barEntries.add(new BarEntry(8,370));
        barEntries.add(new BarEntry(9,650));
        barEntries.add(new BarEntry(10,50));
        barEntries.add(new BarEntry(11,510));
        barEntries.add(new BarEntry(12,480));

        BaseDataSet dataSet=new BarDataSet(barEntries,"Earnings");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10f);

        BarData barData=new BarData((IBarDataSet) dataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Earning Report");
        barChart.animateY(2000);


        firebaseDatabase.getReference().child("Withdraw Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Withdraw user2=snapshot1.getValue(Withdraw.class);
                    if (user2.getFlag().contentEquals("")){
                        user1.add(user2);
                    }
                }
                dialogAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    if (snapshot1.hasChild("Requests")){
                        for (DataSnapshot snapshot2:snapshot1.child("Requests").getChildren()){
                            Request user2=snapshot2.child("Info").getValue(Request.class);
                            if (user2.getFlag().contentEquals("1") && user2.getClickflag().contentEquals("")){
                                user.add(user2);
                            }
                        }
                        currentAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.userlist:
                        Intent i = new Intent(AdminHome.this, AdminUserList.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.partnerlist:
                        Intent j = new Intent(AdminHome.this, AdminPartnerList.class);
                        startActivity(j);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.Feedbacks:
                        Intent k = new Intent(AdminHome.this, AdminFeedback.class);
                        startActivity(k);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.requests:
                        Intent l = new Intent(AdminHome.this, AdminRequests.class);
                        startActivity(l);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }
}