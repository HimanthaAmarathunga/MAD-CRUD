package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MechanicProfileActivity extends AppCompatActivity {
    private Button button1, button2, button3;
    private ImageView imageArrow, imageProfile;
    TextView textViewNOut, textViewProfOut, textViewLocOut, textViewPhoneOut, textViewFieldsOut, textViewTimeOut, textViewQualiOut, textViewDescOut;
    DatabaseReference dbRef;
    Mechanic mechanic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_profile);

        // textViewNOut = findViewById(R.id.textViewNOut);
        textViewProfOut = findViewById(R.id.textViewProfOut);
        textViewLocOut =  findViewById(R.id.textViewLocOut);
        // textViewPhoneOut = findViewById(R.id.textViewPhoneOut);
        textViewFieldsOut = findViewById(R.id.textViewFieldsOut);
        textViewTimeOut =  findViewById(R.id.textViewTimeOut);
        textViewQualiOut = findViewById(R.id.textViewQualiOut);
        textViewDescOut = findViewById(R.id.textViewDescOut);

        button1 = (Button) findViewById(R.id.buttonUpdate);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMechanicUpdate();
                Toast.makeText(getApplicationContext(), "Redirecting to update page...",
                        Toast.LENGTH_LONG).show();
            }
        });

        button2 = (Button) findViewById(R.id.buttonCheck);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAppointments();
                Toast.makeText(getApplicationContext(), "Redirecting to Appointment page... ",
                        Toast.LENGTH_LONG).show();
            }
        });

        button3 = (Button) findViewById(R.id.btnDelete);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegistration();
                DatabaseReference showRef = FirebaseDatabase.getInstance().getReference().child("Mechanic");
                showRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("-MINWTtdihQ8VRnRceVH")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Mechanic").child("-MINWTtdihQ8VRnRceVH");
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        dbRef = FirebaseDatabase.getInstance().getReference().child("Mechanic").child("-MINWTtdihQ8VRnRceVH");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()) {
                    //Add name and profession
                    textViewProfOut.setText("Mechanic");
                    textViewLocOut.setText(dataSnapshot.child("location").getValue().toString());

                    //Add Phone Number

                    //textViewFieldsOut.setText(dataSnapshot.child("Fields").getValue().toString());
//                    ArrayList<String> Fields = (ArrayList<String>) dataSnapshot.child("fields").getValue();
//                    String text = "";
//                    for (String Field : Fields ) {
//                        text += Field + " ";
//                    }
                    textViewTimeOut.setText(dataSnapshot.child("time").getValue().toString());
                    textViewQualiOut.setText(dataSnapshot.child("qualifications").getValue().toString());
                    textViewDescOut.setText(dataSnapshot.child("description").getValue().toString());

                }else {
                    Toast.makeText(getApplicationContext(), "Details Not Displayed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imageArrow = (ImageView) findViewById(R.id.ivBack);
        imageArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    private void openMechanicUpdate() {
        Intent intent = new Intent(this, MechanicEditProfile.class);
        startActivity(intent);
    }
    private void openAppointments() {
        Intent intent = new Intent(this, MyAppointments.class);
        startActivity(intent);
    }
    private void goBack() {
        Intent intent = new Intent(this, MechanicRegistration.class); //Have to put a dynamic onClick
        startActivity(intent);
    }
    private void navigateToRegistration() {
        Intent intent = new Intent(this, MechanicRegistration.class);
        startActivity(intent);
    }
}