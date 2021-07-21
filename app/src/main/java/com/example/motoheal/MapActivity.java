package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.AsyncNotedAppOp;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback{

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    SearchView searchView;
    Toolbar toolbar;
    TextView services, submit,Digitalmeter1,Clutch1,Break12,Accelerator1,Ac1,Lock1,Servicing1,Engine1,Puncture1,Denting1,Lights1,Battery1,Washing1;
    EditText type, company, model, issues;
    //AlertDialog.Builder alertdialog;
    //AlertDialog dialog;
    Dialog dialog;
    Spinner spinner,spinner1,spinner3;
    String partnerType,partnerType1,partnerType2;

    CheckBox digitalMeterRepair,clutch,break1,accelerator,ac,lock,servicing,engine,puncture,dentingpainting,lights,battery,washing;
    String digitalMeterRepair1,clutch1,break11,accelerator1,ac1,lock1,servicing1,engine1,puncture1,dentingpainting1,lights1,battery1,washing1;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    RecyclerView re;
    ArrayList<Request> user1;
    DialogAdapter1 dialogAdapter;

    GoogleMap map;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;

    double currentLat=0,currentLong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        navigationView = findViewById(R.id.nav);
        drawerLayout = findViewById(R.id.draw);
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.search);
        services = (TextView) findViewById(R.id.services);
        //spinner=findViewById(R.id.spinner);
        //button=findViewById(R.id.button);
        re=findViewById(R.id.re);
        user1=new ArrayList<>();
        dialogAdapter=new DialogAdapter1(this,user1);

        re.setAdapter(dialogAdapter);
        re.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        //alertdialog= new AlertDialog.Builder(this);
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.vehicle_details);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        spinner=dialog.findViewById(R.id.spinner);
        spinner1=dialog.findViewById(R.id.spinner1);
        spinner3=dialog.findViewById(R.id.spinner3);
        digitalMeterRepair=dialog.findViewById(R.id.digitalmeterRepair);
        clutch=dialog.findViewById(R.id.clutch);
        break1=dialog.findViewById(R.id.break1);
        accelerator=dialog.findViewById(R.id.accelerator);
        ac=dialog.findViewById(R.id.ac);
        lock=dialog.findViewById(R.id.lock);
        servicing=dialog.findViewById(R.id.servicing);
        engine=dialog.findViewById(R.id.engine);
        puncture=dialog.findViewById(R.id.puncture);
        dentingpainting=dialog.findViewById(R.id.dentingpainting);
        lights=dialog.findViewById(R.id.lights);
        battery=dialog.findViewById(R.id.battery);
        washing=dialog.findViewById(R.id.washing);

        Digitalmeter1=dialog.findViewById(R.id.digitalmeterRepair1);
        Clutch1=dialog.findViewById(R.id.clutch1);
        Break12=dialog.findViewById(R.id.break12);
        Accelerator1=dialog.findViewById(R.id.accelerator1);
        Ac1=dialog.findViewById(R.id.ac1);
        Lock1=dialog.findViewById(R.id.lock1);
        Servicing1=dialog.findViewById(R.id.servicing1);
        Engine1=dialog.findViewById(R.id.engine1);
        Puncture1=dialog.findViewById(R.id.puncture1);
        Denting1=dialog.findViewById(R.id.dentingpainting1);
        Lights1=dialog.findViewById(R.id.lights1);
        Battery1=dialog.findViewById(R.id.battery1);
        Washing1=dialog.findViewById(R.id.washing1);

        model=dialog.findViewById(R.id.model);
        issues=dialog.findViewById(R.id.issues5);

        View layout=navigationView.getHeaderView(0);
        final TextView lay=layout.findViewById(R.id.name3);
        final TextView lay1=layout.findViewById(R.id.number);
        final ImageView lay2=layout.findViewById(R.id.image24);

        firebaseDatabase.getReference().child("Prices").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Digitalmeter1.setText(snapshot.child("Digital Meter Repair").getValue().toString());
                Clutch1.setText(snapshot.child("Clutch").getValue().toString());
                Break12.setText(snapshot.child("Break").getValue().toString());
                Accelerator1.setText(snapshot.child("Accelerator").getValue().toString());
                Ac1.setText(snapshot.child("AC").getValue().toString());
                Lock1.setText(snapshot.child("Lock").getValue().toString());
                Servicing1.setText(snapshot.child("Periodic Servicing").getValue().toString());
                Engine1.setText(snapshot.child("Engine").getValue().toString());
                Puncture1.setText(snapshot.child("Puncture").getValue().toString());
                Denting1.setText(snapshot.child("Denting and Painting").getValue().toString());
                Lights1.setText(snapshot.child("Lights").getValue().toString());
                Battery1.setText(snapshot.child("Battery").getValue().toString());
                Washing1.setText(snapshot.child("Washing").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(dialog.getContext(), R.array.Vehicles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partnerType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(dialog.getContext(), R.array.Models, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partnerType1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(dialog.getContext(), R.array.Gear, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter2);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partnerType2=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Request user2=snapshot1.child("Info").getValue(Request.class);
                    if (user2.getFlag().contentEquals("1") && user2.getClickflag().contentEquals("")){
                        user1.add(user2);
                    }
                }
                dialogAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Personal details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child("firstName").getValue().toString();
                String pho=snapshot.child("phoneNumber").getValue().toString();
                lay.setText(name);
                lay1.setText(pho);
                Picasso.get().load((String) snapshot.child("profilePic").getValue()).placeholder(R.drawable.avatar).into(lay2);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        submit = dialog.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Model=model.getText().toString();
                String Issues=issues.getText().toString();

                if (TextUtils.isEmpty(Model)){
                    model.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(Issues)){
                    issues.setError("Required");
                    return;
                }

                if (digitalMeterRepair.isChecked()){
                    digitalMeterRepair1="Digital Meter Repair";
                }
                else{
                    digitalMeterRepair1="";
                }

                if (clutch.isChecked()){
                    clutch1="Clutch";
                }
                else{
                    clutch1="";
                }

                if (break1.isChecked()){
                    break11="Break";
                }
                else{
                    break11="";
                }

                if (accelerator.isChecked()){
                    accelerator1="Accelerator";
                }
                else{
                    accelerator1="";
                }

                if (ac.isChecked()){
                    ac1="AC";
                }
                else{
                    ac1="";
                }

                if (lock.isChecked()){
                    lock1="Lock";
                }
                else{
                    lock1="";
                }

                if (servicing.isChecked()){
                    servicing1="Servicing";
                }
                else{
                    servicing1="";
                }

                if (engine.isChecked()){
                    engine1="Engine";
                }
                else{
                    engine1="";
                }

                if (puncture.isChecked()){
                    puncture1="Puncture";
                }
                else{
                    puncture1="";
                }

                if (dentingpainting.isChecked()){
                    dentingpainting1="Denting & Painting";
                }
                else{
                    dentingpainting1="";
                }

                if (lights.isChecked()){
                    lights1="Lights";
                }
                else{
                    lights1="";
                }

                if (battery.isChecked()){
                    battery1="Battery";
                }
                else{
                    battery1="";
                }

                if (washing.isChecked()){
                    washing1="Washing";
                }
                else{
                    washing1="";
                }


                Intent i=new Intent(MapActivity.this,NearbyGarages.class);
                i.putExtra("VehicleType",partnerType);
                i.putExtra("VehicleCompany",partnerType1);
                i.putExtra("VehicleModel",Model);
                i.putExtra("digitalMeterRepair",digitalMeterRepair1);
                i.putExtra("clutch",clutch1);
                i.putExtra("break",break11);
                i.putExtra("accelerator",accelerator1);
                i.putExtra("ac",ac1);
                i.putExtra("lock",lock1);
                i.putExtra("servicing",servicing1);
                i.putExtra("engine",engine1);
                i.putExtra("puncture",puncture1);
                i.putExtra("dentingpainting",dentingpainting1);
                i.putExtra("lights",lights1);
                i.putExtra("battery",battery1);
                i.putExtra("washing",washing1);
                i.putExtra("issues",Issues);
                startActivity(i);
                dialog.dismiss();
            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View view = getLayoutInflater().inflate(R.layout.vehicle_details, null);
                //type = findViewById(R.id.type);
                //company = findViewById(R.id.company);
                //model = findViewById(R.id.model);
                //service = findViewById(R.id.service);
                //alertdialog.setView(view);
                //dialog = alertdialog.create();

                dialog.show();
            }
        });


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
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
                    Geocoder geocoder = new Geocoder(MapActivity.this);
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
                        Intent i = new Intent(MapActivity.this, CompleteProfile.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_homeservices:
                        Intent o = new Intent(MapActivity.this, HomeService.class);
                        startActivity(o);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_transactions:
                        Intent k = new Intent(MapActivity.this, UserTransactions.class);
                        startActivity(k);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_feedback:
                        Intent l = new Intent(MapActivity.this, FeedbackActivity.class);
                        startActivity(l);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_policy:
                        Intent n = new Intent(MapActivity.this, PrivacyPolicy.class);
                        startActivity(n);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_help:
                        Intent q = new Intent(MapActivity.this, HelpActivity.class);
                        startActivity(q);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_refer:
                        Intent r = new Intent(MapActivity.this, Refer.class);
                        startActivity(r);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_logout:
                        firebaseAuth.signOut();
                        Intent p = new Intent(MapActivity.this, MainActivity3.class);
                        startActivity(p);
                        finishAffinity();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }


   /* private void createDialog() {
        alertdialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.vehicle_details, null);
        type = findViewById(R.id.type);
        company = findViewById(R.id.company);
        model = findViewById(R.id.model);
        service = findViewById(R.id.service);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        alertdialog.setView(view);
        dialog = alertdialog.create();
        dialog.show();


    }*/

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