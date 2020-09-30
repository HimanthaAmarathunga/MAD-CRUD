package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NurseCareerCreate extends AppCompatActivity {
    CheckBox BabyCare,WoundDressing,FirstAid,ElderlyCare,VigilantObservations,Injections;
    EditText qualifications,description;
    Nurse nurse;
    Spinner spinner,spinner1,spinner2;
    Button nextBtn;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_career_create);

        final DatabaseReference dbRef =FirebaseDatabase.getInstance().getReference().child("Nurse");

        nurse = new Nurse();
        nextBtn=findViewById(R.id.button7);
        BabyCare=findViewById(R.id.checkBox20);
        WoundDressing=findViewById(R.id.checkBox17);
        FirstAid=findViewById(R.id.checkBox21);
        ElderlyCare=findViewById(R.id.checkBox23);
        VigilantObservations=findViewById(R.id.checkBox19);
        Injections=findViewById(R.id.checkBox14);
        spinner= findViewById(R.id.planets_spinner4);
        spinner1= findViewById(R.id.planets_spinner3);
        qualifications=findViewById(R.id.edit_text3);
        description=findViewById(R.id.edit_text4);
        spinner2=findViewById(R.id.planets_spinner5);

        final String s1="BabyCare";
        final String s2="WoundDressing";
        final String s3="FirstAid";
        final String s4="ElderlyCare";
        final String s5="VigilantObservations";
        final String s6="Injections";

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    i=(int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> serviceCategories = new ArrayList<>();

                if(BabyCare.isChecked()){
                    serviceCategories.add("Baby care");
                }
                if(WoundDressing.isChecked()){
                    serviceCategories.add("Wound Dressing");
                }
                if(FirstAid.isChecked()){
                    serviceCategories.add("First Aid");
                }
                if(ElderlyCare.isChecked()){
                    serviceCategories.add("Elderly Care");
                }
                if(VigilantObservations.isChecked()){
                    serviceCategories.add("Vigilant Observations");
                }
                if(Injections.isChecked()){
                    serviceCategories.add("Injections");
                }

//                if(BabyCare.isChecked()){
//                    nurse.setBabyCare(s1);
//                    dbRef.child(String.valueOf(i+1)).setValue(nurse);
//                }
//                else{
//
//                }
//                if(WoundDressing.isChecked()){
//                    nurse.setWoundDressing(s2);
//                    dbRef.child(String.valueOf(i+1)).setValue(nurse);
//                }
//                else{
//
//                }
//                if(FirstAid.isChecked()){
//                    nurse.setFirstAid(s3);
//                    dbRef.child(String.valueOf(i+1)).setValue(nurse);
//                }
//                else{
//
//                }
//                if(ElderlyCare.isChecked()){
//                    nurse.setElderlyCare(s4);
//                    dbRef.child(String.valueOf(i+1)).setValue(nurse);
//                }
//                else{
//
//                }
//                if(VigilantObservations.isChecked()){
//                    nurse.setVigilantObservations(s5);
//                    dbRef.child(String.valueOf(i+1)).setValue(nurse);
//                }
//                else{
//
//                }
//                if(Injections.isChecked()){
//                    nurse.setInjections(s6);
//                    dbRef.child(String.valueOf(i+1)).setValue(nurse);
//                }
//                else{
//
//                }
                try{
                    if(TextUtils.isEmpty(qualifications.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter qualifications",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(description.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter description",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        nurse.setQualifications(qualifications.getText().toString().trim());
                        nurse.setDescription(description.getText().toString().trim());
                        nurse.setLocation(spinner.getSelectedItem().toString().trim());
                        nurse.setAvailableTime(spinner1.getSelectedItem().toString().trim());
                        nurse.setGender(spinner2.getSelectedItem().toString().trim());
                        nurse.setServiceCategories(serviceCategories);

                        dbRef.push().setValue(nurse);
                        Toast.makeText(getApplicationContext(),"Data saved Successfully",Toast.LENGTH_SHORT).show();
                    }
//                    public void onRadioButtonClicked(View view){
//                        boolean checked =((RadioButton) view).isChecked();
//
//                        switch (view.getId()){
//                            case  R.id.radioButton:
//                                if(checked)
//                                    break;
//                            case  R.id.radioButton2:
//                                    if(checked)
//                                        break;
//                        }
//                    }
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
        String [] location =new String[]{"Colombo","Kandy","Kurunegala","Gampaha","Rathnapura"};
        ArrayAdapter<String> adapter =new ArrayAdapter< >( NurseCareerCreate.this, android.R.layout.simple_spinner_dropdown_item,location);
        spinner.setAdapter(adapter);

        String [] time =new String[]{"Day","Night","Both"};
        ArrayAdapter<String> adapter1 =new ArrayAdapter< >( NurseCareerCreate.this, android.R.layout.simple_spinner_dropdown_item,time);
        spinner1.setAdapter(adapter1);

        String [] gender =new String[]{"Male","Female"};
        ArrayAdapter<String> adapter2 =new ArrayAdapter< >( NurseCareerCreate.this, android.R.layout.simple_spinner_dropdown_item,gender);
        spinner2.setAdapter(adapter2);
    }
}