package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PartnerWallet extends AppCompatActivity {

    TextView walletBalance,withdraw,balancedue,paytomoto,submitupi,submitupi1;
    EditText upiname,upiid,goldcoin,amount;
    BottomNavigationView bottomNavigationView;

    BarChart barChart;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Dialog dialog,dialog1;

    ArrayList<Float> arr=new ArrayList<>();
    ArrayList<Float> arr1=new ArrayList<>();
    ArrayList<Float> arr2=new ArrayList<>();
    ArrayList<Float> arr3=new ArrayList<>();

    float sum=0;
    float sum1 =0;
    float sum3=0;
    float sum4=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_wallet);

        walletBalance=findViewById(R.id.walletbalance);
        withdraw=findViewById(R.id.withdraw);
        balancedue=findViewById(R.id.balancedue);
        paytomoto=findViewById(R.id.paytomoto);

        barChart=findViewById(R.id.barchart);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.transaction_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        dialog1=new Dialog(this);
        dialog1.setContentView(R.layout.transaction_dialog1);
        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.setCancelable(false);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.setCanceledOnTouchOutside(true);

        upiname=dialog.findViewById(R.id.upiname);
        upiid=dialog.findViewById(R.id.upiid);
        submitupi=dialog.findViewById(R.id.submitupi);
        goldcoin=dialog.findViewById(R.id.goldcoin);

        amount=dialog1.findViewById(R.id.upiamount);
        submitupi1=dialog1.findViewById(R.id.submitupi1);


        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Transaction Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double Sum=0,Sum1=0,Sum2=0,Sum3=0,Sum4=0,Sum5=0,Sum6=0,Sum7=0,Sum8=0,Sum9=0,Sum10=0,Sum11=0,Sum12=0,Sum13=0,Sum14=0,Sum15=0,Sum16=0,Sum17=0,Sum18=0,Sum19=0,Sum20=0,Sum21=0,Sum22=0,Sum23=0;

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("01")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum4=Sum4+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("01")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum5=Sum5+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("02")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum2=Sum2+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("02")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum3=Sum3+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("03")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum=Sum+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("03")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum1=Sum1+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("04")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum6=Sum6+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("04")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum7=Sum7+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("05")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum8=Sum8+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("05")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum9=Sum9+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("06")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum10=Sum10+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("06")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum11=Sum11+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("07")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum12=Sum12+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("07")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum13=Sum13+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("08")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum14=Sum14+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("08")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum15=Sum15+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("09")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum16=Sum16+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("09")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum17=Sum17+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("10")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum18=Sum18+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("10")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum19=Sum19+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("11")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum20=Sum20+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("11")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum21=Sum21+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Cash").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("12")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum22=Sum22+s2;
                    }
                }

                for (DataSnapshot snapshot1:snapshot.child("Online").getChildren()){
                    if (snapshot1.child("date").getValue().toString().substring(3,5).contentEquals("12")){
                        String s=snapshot1.child("totalPrice").getValue().toString();
                        float s1=Float.parseFloat(s);
                        double s2=s1*0.8;
                        Sum23=Sum23+s2;
                    }
                }


                ArrayList<BarEntry> barEntries=new ArrayList<>();
                barEntries.add(new BarEntry(1,(float) ((float) Sum4+Sum5)));
                barEntries.add(new BarEntry(2,(float) ((float) Sum2+Sum3)));
                barEntries.add(new BarEntry(3, (float) ((float) Sum+Sum1)));
                barEntries.add(new BarEntry(4,(float) ((float) Sum6+Sum7)));
                barEntries.add(new BarEntry(5,(float) ((float) Sum8+Sum9)));
                barEntries.add(new BarEntry(6,(float) ((float) Sum10+Sum11)));
                barEntries.add(new BarEntry(7,(float) ((float) Sum12+Sum13)));
                barEntries.add(new BarEntry(8,(float) ((float) Sum14+Sum15)));
                barEntries.add(new BarEntry(9,(float) ((float) Sum16+Sum17)));
                barEntries.add(new BarEntry(10,(float) ((float) Sum18+Sum19)));
                barEntries.add(new BarEntry(11,(float) ((float) Sum20+Sum21)));
                barEntries.add(new BarEntry(12,(float) ((float) Sum22+Sum23)));

                BaseDataSet dataSet=new BarDataSet(barEntries,"Earnings");
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(10f);

                BarData barData=new BarData((IBarDataSet) dataSet);
                barChart.setFitBars(true);
                barChart.setData(barData);
                barChart.getDescription().setText("Earning Report");
                barChart.animateY(2000);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("Wallet")){
                    for (DataSnapshot snapshot1:snapshot.child("Wallet").getChildren()){
                        String g=snapshot1.child("totalPrice").getValue().toString();
                        float f=Float.parseFloat(g);
                        arr.add(f);
                    }
                }

                if (snapshot.hasChild("Withdraw Requests")){
                    for (DataSnapshot snapshot1:snapshot.child("Withdraw Requests").getChildren()){
                        String g=snapshot1.child("amount").getValue().toString();
                        float f=Float.parseFloat(g);
                        arr1.add(f);
                    }
                }

                if (snapshot.hasChild("Balances Due")){
                    for (DataSnapshot snapshot1:snapshot.child("Balances Due").getChildren()){
                        String g=snapshot1.child("totalPrice").getValue().toString();
                        float t=Float.parseFloat(g);
                        float t2= (float) (t*0.2);
                        arr2.add(t2);
                    }
                }

                if (snapshot.hasChild("Payments To Motoheal")){
                    for (DataSnapshot snapshot1:snapshot.child("Payments To Motoheal").getChildren()){
                        String g=snapshot1.child("totalPrice").getValue().toString();
                        float t=Float.parseFloat(g);
                        arr3.add(t);
                    }
                }

                for (int i=0;i<arr.size();i++){
                    sum=sum+ arr.get(i);
                }

                for (int j=0;j<arr1.size();j++){
                    sum1 = sum1 + arr1.get(j);
                }

                for (int k=0;k<arr2.size();k++){
                    sum3 = sum3 + arr2.get(k);
                }

                for (int l=0;l<arr3.size();l++){
                    sum4 = sum4 + arr3.get(l);
                }

                float sum2=sum-sum1;
                float bc=sum2/50;

                walletBalance.setText(String.valueOf(bc));

                float sum5=sum3-sum4;
                balancedue.setText(String.valueOf(sum5));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        submitupi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Balance=balancedue.getText().toString();
                float B=Float.parseFloat(Balance);
                String Amount=amount.getText().toString();


                if (TextUtils.isEmpty(Amount)){
                    amount.setError("Required");
                    return;
                }

                float C=Float.parseFloat(Amount);
                if ((C>B) || (C<=0)){
                    amount.setError("Invalid Number");
                    return;
                }


                Intent i=new Intent(PartnerWallet.this,PaymentPage2.class);
                i.putExtra("amount",Amount);
                startActivity(i);
                finish();

                dialog1.dismiss();
            }
        });

        submitupi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String UpiName=upiname.getText().toString();
                final String UpiId=upiid.getText().toString();
                String WalletBalance=walletBalance.getText().toString();
                String GoldCoin=goldcoin.getText().toString();


                if (TextUtils.isEmpty(UpiName)){
                    upiname.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(UpiId)){
                    upiid.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(GoldCoin)){
                    goldcoin.setError("Required");
                    return;
                }

                float k=Float.parseFloat(GoldCoin);
                float i=Float.parseFloat(WalletBalance);

                if ((k>i) || (k<=0)){
                    goldcoin.setError("Invalid Number");
                    return;
                }

                final float j=k*50;
                final String ij=String.valueOf(j);

                firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Business Details").child("shopName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Calendar calendar= Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                        final String date=simpleDateFormat.format(calendar.getTime());

                        String Businessname=snapshot.getValue().toString();

                        String k=firebaseDatabase.getReference().child("Withdraw Requests").push().getKey();

                        Withdraw withdraw=new Withdraw(Businessname,UpiName,UpiId,ij,firebaseAuth.getUid(),k,"",date);
                        firebaseDatabase.getReference().child("Withdraw Requests").child(k).setValue(withdraw);

                        Notification1 notification1=new Notification1("Withdraw Request","Partner has sent you withdraw request",firebaseAuth.getUid(),"",date,"",k);
                        firebaseDatabase.getReference().child("Admin").child("Notifications").child("Withdraw Requests").push().setValue(notification1);


                        String key=firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Withdraw Requests").push().getKey();

                        firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Withdraw Requests").child(key).setValue(withdraw);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                //int kl=i-k;
                //walletBalance.setText(String.valueOf(kl));

                Toast.makeText(PartnerWallet.this, "Request Sent Successfully", Toast.LENGTH_SHORT).show();



                dialog.dismiss();
            }
        });

        /*firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Transaction Details").child("Online").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int total=0,total1=0;
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    String totalPrice1=snapshot1.child("totalPrice").getValue().toString();
                    int i=Integer.parseInt(totalPrice1);
                    int j= (int) (i*0.2);
                    int k= (int)(i*0.8);


                    total1=total1+k;
                    total=total+j;
                }
                balancedue.setText(String.valueOf(total));
                totalearnings.setText(String.valueOf(total1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        paytomoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });


    }
}