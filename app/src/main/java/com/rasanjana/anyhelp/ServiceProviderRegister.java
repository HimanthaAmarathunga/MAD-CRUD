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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceProviderRegister extends AppCompatActivity {
    private static String TAG="ServiceProviderRegister";
    EditText fName,lName,email,pNumber,password,cPassword;
    Button button;
    ServiceProvider serviceProvider;
    Spinner spinner;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_register);

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Service Providers");
        serviceProvider=new ServiceProvider();

        fName=findViewById(R.id.firstName);
        lName=findViewById(R.id.lastName);
        email=findViewById(R.id.Email);
        pNumber=findViewById(R.id.pNumber);
        password=findViewById(R.id.password);
        cPassword=findViewById(R.id.cPassowrdS);
        button=findViewById(R.id.registerBtn2);
        spinner=findViewById(R.id.profSpinner);
      //  String selectedValue=spinner.getSelectedItem().toString();

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedValue=spinner.getSelectedItem().toString();
                Log.i(TAG,"onClick:selectedValue="+selectedValue);
                switch (selectedValue){
                    case "Teacher":
                        Intent myIntent =new Intent(ServiceProviderRegister.this,TeacherCareerCreate.class);
                        startActivity(myIntent);
                        break;
                    case "Plumber":
                        Intent myInent1=new Intent(ServiceProviderRegister.this,PlumberCareerCreate.class);
                        startActivity(myInent1);
                        break;
                    case "Visiting Nurse":
                        Intent myInent2=new Intent(ServiceProviderRegister.this,NurseCareerCreate.class);
                        startActivity(myInent2);
                        break;
                    case "Mechanic":
                        Intent myInent3=new Intent(ServiceProviderRegister.this,MechanicRegistration.class);
                        startActivity(myInent3);
                        break;
                }
                try{
                    if (TextUtils.isEmpty(fName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Enter First Name",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(lName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Enter Last Name",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(pNumber.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Enter Phone Number",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(password.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(cPassword.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        serviceProvider.setfName(fName.getText().toString().trim());
                        serviceProvider.setlName(lName.getText().toString().trim());
                        serviceProvider.setProfession(spinner.getSelectedItem().toString().trim());
                        serviceProvider.setEmail(email.getText().toString().trim());
                        serviceProvider.setpNumber(pNumber.getText().toString().trim());
                        serviceProvider.setPassword(password.getText().toString().trim());
                        serviceProvider.setcPassword(cPassword.getText().toString().trim());

                        dbRef.push().setValue(serviceProvider);
                        Toast.makeText(getApplicationContext(),"Service Provider Saved Successfully",Toast.LENGTH_SHORT).show();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_SHORT).show();
                }
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
        String [] professions =new String[]{"Teacher","Plumber","Visiting Nurse","Mechanic"};
        ArrayAdapter<String> adapter =new ArrayAdapter< >( ServiceProviderRegister.this, android.R.layout.simple_spinner_dropdown_item,professions);
        spinner.setAdapter(adapter);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent= new Intent(ServiceProviderRegister.this,TeacherCareerCreate.class);
//                startActivity(myIntent);
//            }
//        });
    }
}