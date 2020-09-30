package com.rasanjana.anyhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlumberCareerCreate extends AppCompatActivity {

    EditText txtName, txtContactNo, txtQualifications, txtDescription;
    Spinner spLocation, spAvailableTime;
    Button btnNext;
    DatabaseReference dbRef;
    Plumber plumber;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber_career_create);

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtName = findViewById(R.id.EtName);
        txtContactNo = findViewById(R.id.EtConNo);
        spLocation =(Spinner) findViewById(R.id.SpLocation);
        spAvailableTime = findViewById(R.id.SpAvailableTime);
        txtQualifications = findViewById(R.id.EtQualification);
        txtDescription = findViewById(R.id.EtDescription);

        btnNext = findViewById(R.id.btnNext);

        plumber = new Plumber();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Plumber");

                try{
                    if(TextUtils.isEmpty(txtName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter your Name", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(txtDescription.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter description", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(txtQualifications.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter Qualification", Toast.LENGTH_SHORT).show();
                    }
                    else {
                    //take inputs from user and assining in to instance
                        plumber.setName(txtName.getText().toString().trim());
                        plumber.setContactNo(Integer.parseInt(txtContactNo.getText().toString().trim()));
                        plumber.setLocation(spLocation.getSelectedItem().toString().trim());
                        plumber.setAvailableTime(spAvailableTime.getSelectedItem().toString().trim());
                        plumber.setQualifications(txtQualifications.getText().toString().trim());
                        plumber.setDescription(txtDescription.getText().toString().trim());

                        //insert in to database
                        dbRef.push().setValue(plumber);

                        //feedback to a user via toast
                        Toast.makeText(getApplicationContext(),"Data Saved Successfully", Toast.LENGTH_SHORT).show();

//move next page
                       Intent myIntent = new Intent(PlumberCareerCreate.this, PlumberProfileActivity.class);
                       startActivity(myIntent);
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalide Contact Number", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
 }