package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NurseProfile extends AppCompatActivity {
    TextView location,qualifications,description, serviceCategories,gender,availableTime;
    private static final String TAG = "NurseProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_profile);
        location=findViewById(R.id.textView27);
        qualifications=findViewById(R.id.textView28);
        description=findViewById(R.id.textView54);
        serviceCategories=findViewById(R.id.textV);
        gender=findViewById(R.id.textV2);
        availableTime=findViewById(R.id.availableTimeN);

        FirebaseDatabase.getInstance().getReference().child("nurse");
        DatabaseReference readRef=FirebaseDatabase.getInstance().getReference().child("Nurse").child("-MHvX05QwQ6bEKxgmUxz");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i(TAG, "onDataChange: ");
                if(snapshot.hasChildren()){
                    ArrayList<String> serviceCategoriesList = (ArrayList<String>) snapshot.child("serviceCategories").getValue();
                    String text = "";
                    for(String serviceCategory : serviceCategoriesList){
                        text += serviceCategory+" ";
                    }
                    Log.i(TAG, "onDataChange: subjects = "+serviceCategoriesList);
                    serviceCategories.setText(text);
                    location.setText(snapshot.child("location").getValue().toString());
                    availableTime.setText(snapshot.child("availableTime").getValue().toString());
                    qualifications.setText(snapshot.child("qualifications").getValue().toString());
                    description.setText(snapshot.child("description").getValue().toString());
                    gender.setText(snapshot.child("gender").getValue().toString());


                }
                else{
                    Toast.makeText(getApplicationContext(),"No source",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}