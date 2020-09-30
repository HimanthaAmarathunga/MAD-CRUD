package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class MechanicEditProfile extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    CheckBox checkBoxCar, checkBoxVan, checkBoxBike, checkBoxTruck, checkBoxMachines;
    Spinner spinnerLocation, spinnerTime;
    ArrayAdapter<String> adapter;
    EditText editTextQualifications, editTextDescription;
    private static final String TAG = "Mechanic Update Form";
    DatabaseReference upRef;
    Mechanic mech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_edit_profile);

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

        upRef = FirebaseDatabase.getInstance().getReference().child("-MII9fnWUEKXDtSpsNZz");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.hasChild("-MII9fnWUEKXDtSpsNZz")) {
//                    try {
//                        if (checkBoxCar.isChecked()) {
//                            mech.setCar(f1);
//                        }else {
//                            //
//                        }
//                        if (checkBoxVan.isChecked()) {
//                            mech.setVan(f2);
//                        }else {
//                            //
//                        }
//                        if (checkBoxBike.isChecked()) {
//                            mech.setBike(f3);
//                        }else {
//                            //
//                        }
//                        if (checkBoxTruck.isChecked()) {
//                            mech.setTruck(f4);
//                        }else {
//                            //
//                        }
//                        if (checkBoxMachines.isChecked()) {
//                            mech.setMachinery(f5);
//                        }else {
//                            //
//                        }
                if (TextUtils.isEmpty(spinnerLocation.getSelectedItem().toString())) {
                    Toast.makeText(MechanicEditProfile.this, "Select Location", Toast.LENGTH_SHORT).show();
                } else if ((TextUtils.isEmpty(spinnerTime.getSelectedItem().toString()))) {
                    Toast.makeText(MechanicEditProfile.this, "Select Time", Toast.LENGTH_SHORT).show();
                } else if ((TextUtils.isEmpty(editTextQualifications.getText().toString()))) {
                    Toast.makeText(MechanicEditProfile.this, "Enter Qualifications", Toast.LENGTH_SHORT).show();
                } else if ((TextUtils.isEmpty(editTextDescription.getText().toString()))) {
                    Toast.makeText(MechanicEditProfile.this, "Enter Description", Toast.LENGTH_SHORT).show();
                } else {
                    mech.setLocation(spinnerLocation.getSelectedItem().toString().trim());
                    mech.setTime(spinnerTime.getSelectedItem().toString().trim());
                    mech.setQualifications(editTextQualifications.getText().toString().trim());
                    mech.setDescription(editTextDescription.getText().toString().trim());
                }


//                    }catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "onCancelled : ");
            }
        });

        button = (Button) findViewById(R.id.buttonUpdateEP);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMechanicProfile();
                Toast.makeText(getApplicationContext(), "Account Edited",
                        Toast.LENGTH_SHORT).show();

//                //Must enter the CheckBoxes
                mech.setLocation(spinnerLocation.getSelectedItem().toString().trim());
                mech.setTime(spinnerTime.getSelectedItem().toString().trim());
                mech.setQualifications(editTextQualifications.getText().toString().trim());
                mech.setDescription(editTextDescription.getText().toString().trim());

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Mechanic").child("-MII9fnWUEKXDtSpsNZz");
                dbRef.setValue(mech);

                //Feedback to the user
                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        imageView = (ImageView) findViewById(R.id.ivBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

    }

    private void openMechanicProfile() {
        Intent intent = new Intent(MechanicEditProfile.this, MechanicProfileActivity.class);
        startActivity(intent);
    }

    private void goBack() {
        Intent intent = new Intent(this, MechanicProfileActivity.class);
        startActivity(intent);
    }

    public void update(View view) {
        if (anyDataChanged()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        }
    }

    private boolean anyDataChanged() {
        if (!editTextQualifications.equals(editTextQualifications.getText().toString())) {
            upRef.child(String.valueOf(editTextQualifications));
        } else {
            return false;
        }
        return false;
    }
}