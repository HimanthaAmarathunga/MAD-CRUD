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

import java.util.ArrayList;
import java.util.List;

public class MechanicEditProfile extends AppCompatActivity {
    Button buttonUpdate;
    private ImageView imageView;
    CheckBox checkBoxCar, checkBoxVan, checkBoxBike, checkBoxTruck, checkBoxMachines;
    Spinner spinnerLocation, spinnerTime;
    ArrayAdapter<CharSequence> locationAdapter, timeAdapter;
    EditText editTextQualifications, editTextDescription;
    private static final String TAG = "MechanicEditProfile";
    String key;
    DatabaseReference upRef;
    Mechanic mech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_edit_profile);

        Intent intent = new Intent();
        key = intent.getStringExtra(MechanicProfileActivity.key);

        checkBoxCar = findViewById(R.id.checkBoxCar);
        checkBoxVan = findViewById(R.id.checkBoxVan);
        checkBoxBike = findViewById(R.id.checkBoxBike);
        checkBoxTruck = findViewById(R.id.checkBoxTruck);
        checkBoxMachines = findViewById(R.id.checkBoxMachines);

        editTextQualifications = findViewById(R.id.etEditQualifications);
        editTextDescription = findViewById(R.id.etEditDescription);

        spinnerLocation = findViewById(R.id.spEditLocation);
        spinnerTime = findViewById(R.id.spEditTime);

        locationAdapter = ArrayAdapter.createFromResource(MechanicEditProfile.this,
                R.array.Location_arrays, android.R.layout.simple_spinner_item);
        spinnerLocation.setAdapter(locationAdapter);

        timeAdapter =ArrayAdapter.createFromResource(MechanicEditProfile.this,
                R.array.Time_arrays, android.R.layout.simple_spinner_item);
        spinnerTime.setAdapter(timeAdapter);

        upRef = FirebaseDatabase.getInstance().getReference().child("Mechanic").child("-MIoGg-FF-dplTzEuy5Q");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "OnDataChange");

                imageView = (ImageView) findViewById(R.id.ivBack);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goBack();
                    }
                });

                Mechanic mech = dataSnapshot.getValue(Mechanic.class);

                ArrayList<String> FieldsList= (ArrayList<String>) dataSnapshot.child("fields").getValue();
                String text =" ";
                for (String Fields : FieldsList) {
                    text += FieldsList + " ";

                        if (Fields.equals("Car")) {
                            checkBoxCar.setChecked(true);
                            Log.i(TAG, "onDataChange: location "+mech.getLocation());
                        }
                        if (Fields.equals("Van")) {
                            checkBoxVan.setChecked(true);
                        }
                        if (Fields.equals("Bike")) {
                            checkBoxBike.setChecked(true);
                        }
                        if (Fields.equals("Truck")) {
                            checkBoxTruck.setChecked(true);
                        }

                        Log.i(TAG, "onDataChange 2: location "+mech.getLocation());
                        if (Fields.equals("Industrial Machinery")) {
                            checkBoxMachines.setChecked(true);
                        }else {

                        int index = locationAdapter.getPosition(mech.getLocation());
                        spinnerLocation.setSelection(index);

                        int index2 = timeAdapter.getPosition(mech.getTime());
                        spinnerTime.setSelection(index2);

                        editTextQualifications.setText(mech.getQualifications());
                        editTextDescription.setText(mech.getDescription());
                       }
                   }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "onCancelled : ");
            }
        });

        buttonUpdate = (Button) findViewById(R.id.buttonUpdateEP);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openMechanicProfile();
                Log.i(TAG,"onClick");
                mech = new Mechanic();

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
                mech.setLocation(spinnerLocation.getSelectedItem().toString().trim());
                mech.setTime(spinnerTime.getSelectedItem().toString().trim());

                mech.setQualifications(editTextQualifications.getText().toString().trim());
                mech.setDescription(editTextDescription.getText().toString().trim());

                upRef.setValue(mech);

//               Feedback to the user
                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

//    private void openMechanicProfile() {
//        Intent intent = new Intent(MechanicEditProfile.this, MechanicProfileActivity.class);
//        startActivity(intent);
//    }

    private void goBack() {
        Intent intent = new Intent(this, MechanicProfileActivity.class);
        startActivity(intent);
    }
}