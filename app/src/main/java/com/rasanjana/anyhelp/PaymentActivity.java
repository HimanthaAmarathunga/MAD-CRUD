package com.rasanjana.anyhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PaymentActivity extends AppCompatActivity {

    private ImageView imageView;
    Button btnStartCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        imageView = (ImageView) findViewById(R.id.ivBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        btnStartCareer = (Button) findViewById(R.id.buttonStartCareer);
        btnStartCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMechanicProfile();
            }
        });
    }

    private void goBack() {
        Intent intent = new Intent(this, MechanicRegistration.class);
        startActivity(intent);
    }

    private void openMechanicProfile() {
        Intent intent = new Intent(this, MechanicProfileActivity.class);
        startActivity(intent);
    }
}