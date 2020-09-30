package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;

public class TeacherProfile extends AppCompatActivity {
    Button button,AcntDltBtn;
    TextView location,qualifications,description, subjects,grade,availableTime;
    DatabaseReference dbRef;

    private static final String TAG = "TeacherProfile";
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        button=findViewById(R.id.button5);
        AcntDltBtn=findViewById(R.id.DltAcntBtn);
        subjects=findViewById(R.id.textV);
        grade=findViewById(R.id.textV2);
        location=findViewById(R.id.textView48);
       availableTime=findViewById(R.id.availableTimeN);
        qualifications=findViewById(R.id.textView28);
        description=findViewById(R.id.textView51);

        FirebaseDatabase.getInstance().getReference().child("teacher");
        DatabaseReference  readRef=FirebaseDatabase.getInstance().getReference().child("Teacher").child("-MHxxMyNU6slARnIjyYY");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i(TAG, "onDataChange: ");
                if(snapshot.hasChildren()){
                    ArrayList<String> subjectList = (ArrayList<String>) snapshot.child("subjects").getValue();
                    String text = "";
                    for(String subject : subjectList){
                        text += subject+" ";
                    }
                    ArrayList<String> gradeList= (ArrayList<String>) snapshot.child("grades").getValue();
                    String text1 = "";
                    for(String  grade : gradeList){
                        text1 += grade+" ";
                    }
                    Log.i(TAG, "onDataChange: subjects = "+subjectList);
                    subjects.setText(text);
                    Log.i(TAG,"OnDataChange: grades ="+gradeList);
                    grade.setText(text1);
                    location.setText(snapshot.child("location").getValue().toString());
                    availableTime.setText(snapshot.child("time").getValue().toString());
                    qualifications.setText(snapshot.child("qualifications").getValue().toString());
                    description.setText(snapshot.child("description").getValue().toString());

                }
                else{
                    Toast.makeText(getApplicationContext(),"No source",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "onCancelled: ");
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
    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(TeacherProfile.this,TeacherAppointments.class);
                startActivity(myIntent);
            }
        });
        AcntDltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(TeacherProfile.this,TeacherProfileUpdate.class);
                startActivity(myIntent);
            }
        });
    }
}