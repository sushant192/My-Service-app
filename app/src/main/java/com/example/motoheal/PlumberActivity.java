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

public class PlumberActivity extends AppCompatActivity {

    TextView maintainance,fitting;
    Dialog dialog;
    EditText issues;
    Button confirm1;
    Spinner spinner;
    String partnerType1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber);

        maintainance=findViewById(R.id.maintainance);
        fitting=findViewById(R.id.fitting);

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.plumber_maintainance);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        issues=dialog.findViewById(R.id.issues5);
        confirm1=dialog.findViewById(R.id.confirm1);
        spinner=dialog.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(dialog.getContext(), R.array.Service, android.R.layout.simple_spinner_item);
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

        confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Issues=issues.getText().toString();

                if (TextUtils.isEmpty(Issues)){
                    issues.setError("Required");
                    return;
                }
                Intent i=new Intent(PlumberActivity.this,NearbyPlumbers.class);
                i.putExtra("Service",partnerType1);
                i.putExtra("Issues",Issues);
                startActivity(i);
                dialog.dismiss();
                finish();
            }
        });

        maintainance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        fitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlumberActivity.this,PlumberFitting.class);
                startActivity(i);
                finish();
            }
        });
    }
}