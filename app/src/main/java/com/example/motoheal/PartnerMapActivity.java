package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartnerMapActivity extends FragmentActivity implements OnMapReadyCallback {

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

    RecyclerView rec;
    ArrayList<User1> user1;
    DialogAdapter dialogAdapter;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    GoogleMap map;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;

    double currentLat=0,currentLong=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_map);

        navigationView = findViewById(R.id.nav);
        drawerLayout = findViewById(R.id.draw);
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.search);
        services = (TextView) findViewById(R.id.services);
        //spinner=findViewById(R.id.spinner);
        //button=findViewById(R.id.button);
        rec=findViewById(R.id.rec);
        user1=new ArrayList<>();
        dialogAdapter=new DialogAdapter(this,user1);

        rec.setAdapter(dialogAdapter);
        rec.setLayoutManager(new LinearLayoutManager(this));

        View layout=navigationView.getHeaderView(0);
        final TextView lay=layout.findViewById(R.id.name2);
        final TextView lay1=layout.findViewById(R.id.phone);
        final ImageView lay2=layout.findViewById(R.id.photo);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();


        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Notifications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){

                    String s=snapshot1.child("flag").getValue().toString();

                    if (s.contentEquals("")){
                        String Name=snapshot1.child("name").getValue().toString();
                        String Body=snapshot1.child("body").getValue().toString();

                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                            NotificationChannel channel=new NotificationChannel("My notification","My Notification",NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager manager=getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(channel);
                        }

                        NotificationCompat.Builder builder=new NotificationCompat.Builder(PartnerMapActivity.this,"My notification").setSmallIcon(R.drawable.ic_baseline_message_24).setContentTitle(Name).setContentText(Body).setAutoCancel(true);
                        Intent i=new Intent(PartnerMapActivity.this,Transactions.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        PendingIntent pendingIntent=PendingIntent.getActivity(PartnerMapActivity.this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);

                        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(PartnerMapActivity.this);
                        notificationManagerCompat.notify(1,builder.build());

                        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Notifications").child("Cash").child(snapshot1.getKey()).child("flag").setValue("1");
                    }

                }


                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){

                    String s=snapshot1.child("flag").getValue().toString();

                    if (s.contentEquals("")){
                        String Name=snapshot1.child("name").getValue().toString();
                        String Body=snapshot1.child("body").getValue().toString();

                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                            NotificationChannel channel=new NotificationChannel("My notification1","My Notification1",NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager manager=getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(channel);
                        }

                        NotificationCompat.Builder builder=new NotificationCompat.Builder(PartnerMapActivity.this,"My notification1").setSmallIcon(R.drawable.ic_baseline_message_24).setContentTitle(Name).setContentText(Body).setAutoCancel(true);
                        Intent i=new Intent(PartnerMapActivity.this,OnlineTransactions.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        PendingIntent pendingIntent=PendingIntent.getActivity(PartnerMapActivity.this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);

                        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(PartnerMapActivity.this);
                        notificationManagerCompat.notify(1,builder.build());

                        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Notifications").child("Online").child(snapshot1.getKey()).child("flag").setValue("1");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReference().child("Requests").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    User1 user2=snapshot1.child("Info").getValue(User1.class);
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

        firebaseDatabase.getReference().child("Partners").child("partners").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child(firebaseAuth.getUid()).child("Business Details").child("shopName").getValue().toString();
                String pho=snapshot.child(firebaseAuth.getUid()).child("Business Details").child("shopNumber").getValue().toString();
                lay.setText(name);
                lay1.setText(pho);

                Picasso.get().load((String) snapshot.child(firebaseAuth.getUid()).child("Business Details").child("profilepic").getValue()).placeholder(R.drawable.avatar).into(lay2);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        //alertdialog= new AlertDialog.Builder(this);
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.vehicle_details);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        submit = dialog.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PartnerMapActivity.this,NearbyGarages.class);
                startActivity(i);
                dialog.dismiss();
            }
        });


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(PartnerMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(PartnerMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(PartnerMapActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_profile:
                        Intent i = new Intent(PartnerMapActivity.this, CompleteProfile1.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_workers:
                        Intent j = new Intent(PartnerMapActivity.this, CoWorkers.class);
                        startActivity(j);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_wallet:
                        Intent k = new Intent(PartnerMapActivity.this, PartnerWallet.class);
                        startActivity(k);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_pending:
                        Intent l = new Intent(PartnerMapActivity.this, Transactions.class);
                        startActivity(l);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_feedback:
                        Intent m = new Intent(PartnerMapActivity.this, FeedbackActivity.class);
                        startActivity(m);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.terms:
                        Intent n = new Intent(PartnerMapActivity.this, PrivacyPolicy.class);
                        startActivity(n);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.help:
                        Intent o = new Intent(PartnerMapActivity.this, HelpActivity.class);
                        startActivity(o);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout:
                        firebaseAuth.signOut();
                        Intent p = new Intent(PartnerMapActivity.this, MainActivity3.class);
                        startActivity(p);
                        finishAffinity();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }

    private void getCurrentLocation() {

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location!=null){
                    //when location is not equal to null
                    //get current latitude and longitude

                    currentLat=location.getLatitude();
                    currentLong=location.getLongitude();


                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                            MarkerOptions options=new MarkerOptions().position(latLng).title("Here");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            googleMap.addMarker(options);
                        }
                    });

                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==44){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
    }
}