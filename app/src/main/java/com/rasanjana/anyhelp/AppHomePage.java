package com.rasanjana.anyhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//import com.google.firebase.auth.FirebaseAuth;

public class AppHomePage extends AppCompatActivity {
    Button btnTeacher, btnNurse, btnMechanic, btnPlumber, btnLogOut;
    private static final String TAG = "AppHomePage";
    public static final String profession = "profession";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home_page);

        btnTeacher = findViewById(R.id.teacherPro);
        btnNurse = findViewById(R.id.nursePro);
        btnMechanic = findViewById(R.id.mechanicPro);
        btnPlumber = findViewById(R.id.plumberPro);
        btnLogOut = findViewById(R.id.logout);

        Log.i(TAG, "btnTeacher: "+btnTeacher);

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        btnLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            }
//        });

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppHomePage.this, UserMenuActivity.class);
                String Profession = "teacher";
                intent.putExtra(profession, Profession);
                startActivity(intent);
            }
        });
        btnNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppHomePage.this, UserMenuActivity.class);
                String Profession = "nurse";
                intent.putExtra(profession, Profession);
                startActivity(intent);
            }
        }); btnMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppHomePage.this, UserMenuActivity.class);
                String Profession = "mechanic";
                intent.putExtra(profession, Profession);
                startActivity(intent);
            }
        }); btnPlumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppHomePage.this, UserMenuActivity.class);
                String Profession = "plumber";
                intent.putExtra(profession, Profession);
                startActivity(intent);
            }
        });
    }
}