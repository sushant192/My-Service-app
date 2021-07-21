package com.example.motoheal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ElectricianActivity extends AppCompatActivity {

    TextView maintainance,fitting;
    Dialog dialog;
    EditText Screplace,issues3;
    Button confirm4;

    Spinner spinner;
    String partnerType1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician);

        maintainance=findViewById(R.id.maintainance);
        fitting=findViewById(R.id.fitting);

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.electrician_maintainance);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        issues3=dialog.findViewById(R.id.issues3);
        confirm4=dialog.findViewById(R.id.confirm4);
        spinner=dialog.findViewById(R.id.spinner5);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(dialog.getContext(), R.array.Service1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partnerType1=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        maintainance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        confirm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Issues=issues3.getText().toString();

                if (TextUtils.isEmpty(Issues)){
                    issues3.setError("Required");
                    return;
                }

                Intent i=new Intent(ElectricianActivity.this,NearbyElectricians.class);
                i.putExtra("Service",partnerType1);
                i.putExtra("Issues",Issues);
                startActivity(i);
                dialog.dismiss();
                finish();
            }
        });

        fitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ElectricianActivity.this,ElectricianFitting.class);
                startActivity(i);
                finish();
            }
        });
    }
}