package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherProfileUpdate extends AppCompatActivity {
    private  static String TAG ="TeacherProfileUpdate";
    EditText description,qualifications;
    Spinner locationSpinner,availableTimeSpinner;
    CheckBox cbSinhala,cbEnglish;
    Button TeacherProfileUpdate;
    Teacher teacher;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile_update);

        description=findViewById(R.id.edit_text2);
        qualifications=findViewById(R.id.edit_text);
        locationSpinner=findViewById(R.id.planets_spinner);
        availableTimeSpinner=findViewById(R.id.planets_spinner2);
        TeacherProfileUpdate=findViewById(R.id.TeacherUpdateBtn);

        DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("Teacher").child("-MHxxMyNU6slARnIjyYY");
        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.i(TAG,"OnDataChanged");
//                ArrayList<String> subjectList=(ArrayList<String>) snapshot.child("subjects").getValue();
//                String text ="";
//                for (String subject :subjectList){
//                    text += subject+" ";
//                    if (subject.equals("Sinhala")){
//                        cbSinhala.setChecked(true);
//                    }
//                    if (subject.equals("English")){
//                        cbEnglish.setChecked(true);
//                    }
//                }

                description.setText(snapshot.child("description").getValue().toString());
                qualifications.setText(snapshot.child("qualifications").getValue().toString());
                //locationSpinner.;
                //locationSpinner.set
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG,"onCancelled");
            }
        });
        TeacherProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick");
                teacher=new Teacher();

                teacher.setDescription(description.getText().toString().trim());
                teacher.setQualifications(qualifications.getText().toString().trim());

                dbRef =FirebaseDatabase.getInstance().getReference().child("Teacher").child("-MHvNs8-dxIrp9dW9J7h");
                dbRef.setValue(teacher);

                Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT).show();

            }
        });
    }
}