package com.example.motoheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

public class PaymentPage2 extends AppCompatActivity {

    LinearLayout paytm,amazonpay;
    Uri uri;

    private static String totalPrice,workingPrice,basePrice,travelFair,ownerId;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

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
        setContentView(R.layout.activity_payment_page2);

        paytm=findViewById(R.id.paytm);
        amazonpay=findViewById(R.id.amazonpay);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();


        totalPrice=getIntent().getStringExtra("amount");

        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uri = getUpiPaymentUri(name, upiId, transactionNote, amount);
                payWithPaytm();

            }
        });



        amazonpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = getUpiPaymentUri(name, upiId, transactionNote, amount);
                payWithAmazon();

            }
        });
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

    private void payWithPaytm() {
        if (isAppInstalled(this, PAYTM_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(PAYTM_PACKAGE_NAME);
            startActivityForResult(intent, PAYTM_REQUEST_CODE);
        } else {
            Toast.makeText(PaymentPage2.this, "Please Install Paytm", Toast.LENGTH_SHORT).show();
        }
    }
    private void payWithAmazon() {
        if (isAppInstalled(this, AMAZON_PACKAGE_NAME)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(AMAZON_PACKAGE_NAME);
            startActivityForResult(intent, AMAZON_REQUEST_CODE);
        } else {
            Toast.makeText(PaymentPage2.this, "Please Install Amazon Pay", Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }

        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(PaymentPage2.this, "Transaction Successful", Toast.LENGTH_SHORT).show();

            if (status.contentEquals("success")){

                String key=firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Withdraw Requests").push().getKey();

                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                final String date=simpleDateFormat.format(calendar.getTime());

                Transaction transaction=new Transaction("Online","","",totalPrice,firebaseAuth.getUid(),key,date);
                firebaseDatabase.getReference().child("Partners").child("partners").child(firebaseAuth.getUid()).child("Payments To Motoheal").child(key).setValue(transaction);

                Intent i=new Intent(PaymentPage2.this,PartnerMapActivity.class);
                startActivity(i);
                finish();
            }
        }

        else {
            Toast.makeText(PaymentPage2.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
        }
    }
}