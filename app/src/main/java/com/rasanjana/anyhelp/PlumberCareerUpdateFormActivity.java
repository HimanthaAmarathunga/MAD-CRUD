package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlumberCareerUpdateFormActivity extends AppCompatActivity {

    private static final String TAG = "PlumberCareerUpdateForm";

    EditText txtName, txtContactNo, txtQualifications, txtDescription;
    Spinner spLocation, spAvailableTime;
    Button btnUpdate;
    DatabaseReference dbRef;
    Plumber plumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber_career_update_form);

        txtName = findViewById(R.id.EtName);
        txtContactNo = findViewById(R.id.EtConNo);
        txtQualifications = findViewById(R.id.EtQualification);
        txtDescription = findViewById(R.id.EtDescription);
        spLocation = findViewById(R.id.SpLocation);
        spAvailableTime = findViewById(R.id.SpAvailableTime);
        btnUpdate = findViewById(R.id.btnUpdate);


        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Plumber").child("-MH_YnSkx6vYmTwwfmXm");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i(TAG, "onDataChange: ");
                txtName.setText(dataSnapshot.child("name").getValue().toString());

                //spLocation.setAdapter(dataSnapshot.child("contactNo").getValue());
                //txtLoction.setText(dataSnapshot.child("location").getValue().toString());
                txtContactNo.setText(dataSnapshot.child("contactNo").getValue().toString());
                //txtAvailableTime.setText(dataSnapshot.child("availableTime").getValue().toString());
                txtQualifications.setText(dataSnapshot.child("qualifications").getValue().toString());
                txtDescription.setText(dataSnapshot.child("description").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG, "onCancelled: ");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: ");
                plumber = new Plumber();

                plumber.setName(txtName.getText().toString().trim());
                plumber.setContactNo(Integer.parseInt(txtContactNo.getText().toString().trim()));
                plumber.setLocation(spLocation.getSelectedItem().toString().trim());
                plumber.setAvailableTime(spAvailableTime.getSelectedItem().toString().trim());
                plumber.setQualifications(txtQualifications.getText().toString().trim());
                plumber.setDescription(txtDescription.getText().toString().trim());

                dbRef = FirebaseDatabase.getInstance().getReference().child("Plumber").child("-MH_YnSkx6vYmTwwfmXm");
                dbRef.setValue(plumber);

                //Feedback to the user via toast
                Toast.makeText(getApplicationContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}