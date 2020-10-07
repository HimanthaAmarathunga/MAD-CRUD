package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MechanicRegistration extends AppCompatActivity {
    Button buttonNxt;
    CheckBox checkBoxCar, checkBoxVan, checkBoxBike, checkBoxTruck, checkBoxMachines;
    Spinner spinnerLocation, spinnerTime;
    EditText editTextQualifications, editTextDescription;
    ImageView imageView;
    DatabaseReference dbRef;
    Mechanic mech;
    int i=0;
    private static final String TAG = "MechanicRegistration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_registration);

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
        buttonNxt = findViewById(R.id.btnNextMechanic);

        mech = new Mechanic();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Mechanic");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttonNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> Fields = new ArrayList<>();

                    if (checkBoxCar.isChecked()) {
                        Fields.add("Car");
                    }
                Log.i(TAG, "car: "+checkBoxCar.isChecked());
                    if (checkBoxVan.isChecked()) {
                        Fields.add("Van");
                    }
                    if (checkBoxBike.isChecked()) {
                        Fields.add("Bike");
                    }
                    if (checkBoxTruck.isChecked()) {
                        Fields.add("Truck");
                    }
                    if (checkBoxMachines.isChecked()) {
                        Fields.add("Industrial Machinery");
                    }
                try {
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
                        mech.setFields(Fields);

                        dbRef.push().setValue(mech);
                        Toast.makeText(getApplicationContext(),"Account Created", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "Mech: "+mech.getFields()+" "+mech.getDescription());
                    }

                }
                catch (NumberFormatException e) {
                   // e.printStackTrace();
                }

                loadPaymentPage();   //Must change to Payment class

                imageView = (ImageView) findViewById(R.id.ivBack);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goBack();
                    }
                });
            }
        });
    }

    private void loadPaymentPage() {
        Intent intent = new Intent(this, MechanicProfileActivity.class);
        startActivity(intent);

    }

    private void goBack() {
        Intent intent = new Intent(this, MechanicRegistration.class); //MUST CHANGE TO APPROPRIATE CLASS
        startActivity(intent);
    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String text = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}