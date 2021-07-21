package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaymentPage extends AppCompatActivity {

    private static String totalPrice,workingPrice,basePrice,travelFair,ownerId;
    private static String m1;
    private static String n1;
    private static float s1,s2;
    LinearLayout paytm,amazonpay;
    public static final String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    public static final String PAYTM_PACKAGE_NAME ="net.one97.paytm";
    public static final String AMAZON_PACKAGE_NAME ="in.amazon.mShop.android.shopping";
    int GOOGLE_PAY_REQUEST_CODE = 123;
    int PAYTM_REQUEST_CODE = 1234;
    int AMAZON_REQUEST_CODE = 12345;
    String amount="10";
    String name = "Motoheal";
    String upiId = "8219931720@okbizaxis";
    String transactionNote = "pay test";
    public String status;
    //public String totalPrice;
    Uri uri;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        paytm=findViewById(R.id.paytm);
        amazonpay=findViewById(R.id.amazonpay);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        workingPrice=getIntent().getStringExtra("workingprice");
        basePrice=getIntent().getStringExtra("baseprice");
        travelFair=getIntent().getStringExtra("travelfair");
        totalPrice=getIntent().getStringExtra("totalprice");
        m1=getIntent().getStringExtra("m1");
        n1=getIntent().getStringExtra("n1");
        final int s=Integer.parseInt(totalPrice);
        s1= (int) (s*0.8);
        float f=50;
        s2=s1/f;
        ownerId=getIntent().getStringExtra("ownerid");

        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = getUpiPaymentUri(name, upiId, transactionNote, amount);
                payWithPaytm();

                /*if (status.contentEquals("success")){

                    firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Requests").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot snapshot1:snapshot.getChildren()){
                                String s=snapshot1.child("Info").child("flag").getValue().toString();
                                String s1=snapshot1.getKey();
                                String s2=snapshot1.child("Info").child("clickflag").getValue().toString();
                                String s3=snapshot1.child("Info").child("businessName").getValue().toString();

                                if (s.contentEquals("1") && s1.contentEquals(m1) && s2.contentEquals("") && s3.contentEquals(n1)){
                                    firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Requests").child(snapshot1.getKey()).child("Info").child("clickflag").setValue("1");
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });


                    String key=firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Payments").child("Online").push().getKey();

                    Calendar calendar= Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                    final String date=simpleDateFormat.format(calendar.getTime());

                    Transaction transaction=new Transaction("Online",basePrice,travelFair,totalPrice,ownerId,key,date);
                    firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Payments").child("Online").child(key).setValue(transaction);

                    Transaction1 transaction1=new Transaction1("Online",basePrice,travelFair,totalPrice,firebaseAuth.getUid(),key,date);
                    firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Transaction Details").child("Online").child(key).setValue(transaction1);

                    //ownerid--wallet--same values;

                    Wallet wallet=new Wallet(String.valueOf(s1),String.valueOf(s2),ownerId,date,key);
                    firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Wallet").child(key).setValue(wallet);
                    firebaseDatabase.getReference().child("Admin").child("Gold coins").child(key).setValue(wallet);


                }*/

            }
        });



        amazonpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = getUpiPaymentUri(name, upiId, transactionNote, amount);
                payWithAmazon();

            }
        });


        /*googlepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = getUpiPaymentUri(name, upiId, transactionNote, amount);
                payWithGPay();

            }
        });*/
    }

    private static Uri getUpiPaymentUri(String name, String upiId, String transactionNote, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("mc", "")
                .appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", transactionNote)
                .appendQueryParameter("am", totalPrice)
                .appendQueryParameter("cu", "INR")
                .build();
    }

    private void payWithGPay() {
        if (isAppInstalled(this, GOOGLE_PAY_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
        } else {
            Toast.makeText(PaymentPage.this, "Please Install GPay", Toast.LENGTH_SHORT).show();
        }
    }

    private void payWithPaytm() {
        if (isAppInstalled(this, PAYTM_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(PAYTM_PACKAGE_NAME);
            startActivityForResult(intent, PAYTM_REQUEST_CODE);
        } else {
            Toast.makeText(PaymentPage.this, "Please Install Paytm", Toast.LENGTH_SHORT).show();
        }
    }
    private void payWithAmazon() {
        if (isAppInstalled(this, AMAZON_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(AMAZON_PACKAGE_NAME);
            startActivityForResult(intent, AMAZON_REQUEST_CODE);
        } else {
            Toast.makeText(PaymentPage.this, "Please Install Amazon Pay", Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }

        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(PaymentPage.this, "Transaction Successful", Toast.LENGTH_SHORT).show();

            if (status.contentEquals("success")){

                firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Requests").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            String s=snapshot1.child("Info").child("flag").getValue().toString();
                            String s1=snapshot1.getKey();
                            String s2=snapshot1.child("Info").child("clickflag").getValue().toString();
                            String s3=snapshot1.child("Info").child("businessName").getValue().toString();

                            if (s.contentEquals("1") && s1.contentEquals(m1) && s2.contentEquals("") && s3.contentEquals(n1)){
                                firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Requests").child(snapshot1.getKey()).child("Info").child("clickflag").setValue("1");
                                firebaseDatabase.getReference().child("Requests").child(ownerId).child(snapshot1.getKey()).child("Info").child("flag").setValue("1");
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });


                String key=firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Payments").child("Online").push().getKey();

                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                final String date=simpleDateFormat.format(calendar.getTime());

                Transaction transaction=new Transaction("Online",basePrice,travelFair,totalPrice,ownerId,key,date);
                firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("Payments").child("Online").child(key).setValue(transaction);
                firebaseDatabase.getReference().child("Admin").child("Transaction Details").child("Online").child(key).setValue(transaction);

                Transaction1 transaction1=new Transaction1("Online",basePrice,travelFair,totalPrice,firebaseAuth.getUid(),key,date);
                firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Transaction Details").child("Online").child(key).setValue(transaction1);

                Notification1 notification1=new Notification1("Online Payments","Online payment Received",firebaseAuth.getUid(),"",date,"",key);
                firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Notifications").child("Online").child(key).setValue(notification1);

                Wallet wallet=new Wallet(String.valueOf(s1),String.valueOf(s2),ownerId,date,key);
                firebaseDatabase.getReference().child("Partners").child("partners").child(ownerId).child("Wallet").child(key).setValue(wallet);
                firebaseDatabase.getReference().child("Admin").child("Gold coins").child(key).setValue(wallet);

                Intent i=new Intent(PaymentPage.this,MapActivity.class);
                startActivity(i);
                finish();
            }
        }

        else {

            Toast.makeText(PaymentPage.this, "Transaction Failed", Toast.LENGTH_SHORT).show();

            Intent i=new Intent(PaymentPage.this,MapActivity.class);
            startActivity(i);
            finish();
        }
    }
}