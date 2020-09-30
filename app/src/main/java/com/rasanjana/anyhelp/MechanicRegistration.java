package com.rasanjana.anyhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MechanicRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button buttonNxt;
    CheckBox checkBoxCar, checkBoxVan, checkBoxBike, checkBoxTruck, checkBoxMachines;
    Spinner spinnerLocation, spinnerTime;
    EditText editTextQualifications, editTextDescription;
    DatabaseReference dbRef;
    Mechanic mech;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxCar = findViewById(R.id.checkBoxCar);
        checkBoxVan = findViewById(R.id.checkBoxVan);
        checkBoxBike = findViewById(R.id.checkBoxBike);
        checkBoxTruck = findViewById(R.id.checkBoxTruck);
        checkBoxMachines = findViewById(R.id.checkBoxMachines);

        final String f1 = "Car";
        final String f2 = "Van";
        final String f3 = "Bike";
        final String f4 = "Truck";
        final String f5 = "Industrial Machinery";

        spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        spinnerTime = (Spinner) findViewById(R.id.spinnerTime);

        editTextQualifications = findViewById(R.id.editTextQualifications);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonNxt = findViewById(R.id.btnNext);

        mech = new Mechanic();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Mechanic");
        buttonNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPaymentPage();   //Must change to Payment class
                Toast.makeText(getApplicationContext(),"Account Created",
                        Toast.LENGTH_LONG).show();
                List<String> Fields = new ArrayList<>();

                try {
                    if (checkBoxCar.isChecked()) {
                        Fields.add("Car");
                    }else {
                        //
                    }
                    if (checkBoxVan.isChecked()) {
                        Fields.add("Van");
                    }else {
                        //
                    }
                    if (checkBoxBike.isChecked()) {
                        Fields.add("Bike");
                    }else {
                        //
                    }
                    if (checkBoxTruck.isChecked()) {
                        Fields.add("Truck");
                    }else {
                        //
                    }
                    if (checkBoxMachines.isChecked()) {
                        Fields.add("Industrial Machinery");
                    }else {
                        //
                    }
                    if (TextUtils.isEmpty(spinnerLocation.getSelectedItem().toString())) {
                        Toast.makeText(MechanicRegistration.this, "Select Location", Toast.LENGTH_SHORT).show();
                    }else if ((TextUtils.isEmpty(spinnerTime.getSelectedItem().toString()))) {
                        Toast.makeText(MechanicRegistration.this, "Select Time", Toast.LENGTH_SHORT).show();
                    }else if ((TextUtils.isEmpty(editTextQualifications.getText().toString()))){
                        Toast.makeText(MechanicRegistration.this, "Enter Qualifications", Toast.LENGTH_SHORT).show();
                    }else if  ((TextUtils.isEmpty(editTextDescription.getText().toString()))){
                        Toast.makeText(MechanicRegistration.this, "Enter Description", Toast.LENGTH_SHORT).show();
                    }else {
                        mech.setLocation(spinnerLocation.getSelectedItem().toString().trim());
                        mech.setTime(spinnerTime.getSelectedItem().toString().trim());
                        mech.setQualifications(editTextQualifications.getText().toString().trim());
                        mech.setDescription(editTextDescription.getText().toString().trim());
                    }
                    dbRef.push().setValue(mech);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }

    private void loadPaymentPage() {
        Intent intent = new Intent(this, MechanicProfileActivity.class);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}