package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRegister extends AppCompatActivity {
    EditText fName,lName,email,pNumber,password,cPassword;
    Button button;
    Customer customer;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        final DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Customers");
        customer=new Customer();

        button=findViewById(R.id.registerBtn);
        fName=findViewById(R.id.fName);
        lName=findViewById(R.id.lName);
        email=findViewById(R.id.email);
        pNumber=findViewById(R.id.pNumber);
        password=findViewById(R.id.password);
        cPassword=findViewById(R.id.cPassword);

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent= new Intent(UserRegister.this,AppHomePage.class);
                startActivity(myIntent);
                try{
                    if(TextUtils.isEmpty(fName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter First Name",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(lName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter Last Name",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter Email",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(pNumber.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter Phone Number",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(password.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter Password",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(cPassword.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter Confirm Password",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        customer.setfName(fName.getText().toString().trim());
                        customer.setlName(lName.getText().toString().trim());
                        customer.setEmail(email.getText().toString().trim());
                        customer.setpNumber(pNumber.getText().toString().trim());
                        customer.setPassword(password.getText().toString().trim());
                        customer.setcPassword(cPassword.getText().toString().trim());

                        dbRef.push().setValue(customer);
                        Toast.makeText(getApplicationContext(),"Data saved Successfully",Toast.LENGTH_SHORT).show();
                    }
                }catch (NumberFormatException e ){
                    Toast.makeText(getApplicationContext(),"Invalid Contact Number", Toast.LENGTH_SHORT).show();
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
}