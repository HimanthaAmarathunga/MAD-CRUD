package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class TeacherCareerCreate extends AppCompatActivity {
    CheckBox sinhala,english,science,physics,tamil,biology,mathematics,chemistry,accounting,economics,primary,sixToOl,al;
    EditText qualifications,description;
    Teacher teacher;
    Spinner spinner,spinnerTime;
    Button nextBtn;
    int i =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_career_create);

        final DatabaseReference dbRef=FirebaseDatabase.getInstance().getReference().child("Teacher");

        teacher = new Teacher();
        nextBtn=findViewById(R.id.button3);
        sinhala=findViewById(R.id.c1);
        english=findViewById(R.id.c2);
        science=findViewById(R.id.c3);
        physics=findViewById(R.id.c4);
        tamil=findViewById(R.id.c5);
        biology=findViewById(R.id.c6);
        mathematics=findViewById(R.id.c7);
        chemistry=findViewById(R.id.c8);
        accounting=findViewById(R.id.c9);
        economics=findViewById(R.id.c10);
        primary=findViewById(R.id.checkBox9);
        sixToOl=findViewById(R.id.checkBox10);
        al=findViewById(R.id.checkBox11);
        spinner=findViewById(R.id.planets_spinner);
        spinnerTime=findViewById(R.id.planets_spinner2);
        qualifications=findViewById(R.id.edit_text);
        description=findViewById(R.id.edit_text2);

        final String s1="Sinhala";
        final String s2="English";
        final String s3="Science";
        final String s4="Physics";
        final String s5="Tamil";
        final String s6="Biology";
        final String s7="Mathematics";
        final String s8="Chemistry";
        final String s9= "Accounting";
        final String s10="Economics";
        final String  g1="Primary";
        final String g2="Six to O/L";
        final String g3="A/L";

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> subjects = new ArrayList<>();

                if(sinhala.isChecked()){
                    subjects.add("Sinhala");
                }
                if(english.isChecked()){
                    subjects.add("English");
                }
                if(tamil.isChecked()){
                    subjects.add("Tamil");
                }
                if(science.isChecked()){
                    subjects.add("Science");
                }
                if(physics.isChecked()){
                    subjects.add("Physics");
                }
                if(biology.isChecked()){
                    subjects.add("Biology");
                }
                if(mathematics.isChecked()){
                    subjects.add("Mathematics");
                }
                if(chemistry.isChecked()){
                    subjects.add("Chemistry");
                }
                if(accounting.isChecked()){
                    subjects.add("Accounting");
                }
                if(economics.isChecked()){
                    subjects.add("Economics");
                }

                List<String> grades = new ArrayList<>();

                if(primary.isChecked()){
                    grades.add("Primary");
                }
                if(sixToOl.isChecked()){
                    grades.add("Six-OL");
                }
                if(al.isChecked()){
                    grades.add("A/L");
                }


                try{
                    if(TextUtils.isEmpty(qualifications.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter qualifications",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(description.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter description",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        teacher.setQualifications(qualifications.getText().toString().trim());
                        teacher.setDescription(description.getText().toString().trim());
                        teacher.setLocation(spinner.getSelectedItem().toString().trim());
                        teacher.setTime(spinnerTime.getSelectedItem().toString().trim());
                        teacher.setSubjects(subjects);
                        teacher.setGrades(grades);

                        dbRef.push().setValue(teacher);
                        Toast.makeText(getApplicationContext(),"Data saved Successfully",Toast.LENGTH_SHORT).show();
                    }
                }catch (NumberFormatException e ){}
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
        String [] location =new String[]{"Colombo","Kandy","Kurunegala","Gampaha","Galle","Kegalle","Rathnapura","Hambanthota","Kaluthara","Matara"};
        ArrayAdapter<String> adapter =new ArrayAdapter< >( TeacherCareerCreate.this, android.R.layout.simple_spinner_dropdown_item,location);
        spinner.setAdapter(adapter);

        String [] time =new String[]{"Day","Night","Both"};
        ArrayAdapter<String> adapter1 =new ArrayAdapter< >( TeacherCareerCreate.this, android.R.layout.simple_spinner_dropdown_item,time);
        spinnerTime.setAdapter(adapter1);


    }
}