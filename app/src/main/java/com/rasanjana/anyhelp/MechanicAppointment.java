package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.List;

public class MechanicAppointment extends AppCompatActivity {

    String key;
    TableLayout tableLayout;
    Mechanic mech;
    DatabaseReference dbRef;

    SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_appointment);

        Intent intent = getIntent();
        key = intent.getStringExtra(MechanicProfileActivity.key);

        tableLayout = findViewById(R.id.appointmentTableMechanic);
        tableLayout.setStretchAllColumns(true);
        tableLayout.bringToFront();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Mechanic").child(key);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    mech = dataSnapshot.getValue(Mechanic.class);
                    if (mech!=null){
                        showAppointments(mech.getAppointments());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showAppointments(final List<MyAppointments> appointments) {

        tableLayout.removeAllViews();
        TableRow header = new TableRow(this);
        TextView h1 = new TextView(this);
        h1.setText("Date");
        TextView h2 = new TextView(this);
        h2.setText("Location");
        TextView h3 = new TextView(this);
        h3.setText("Task");

        header.addView(h1);
        header.addView(h2);
        header.addView(h3);

        h1.setBackgroundColor(Color.LTGRAY);
        h2.setBackgroundColor(Color.LTGRAY);
        h3.setBackgroundColor(Color.LTGRAY);

        h1.setPadding(20, 20,20,20);
        h2.setPadding(20, 20,20,20);
        h3.setPadding(20, 20,20,20);

        tableLayout.addView(header);

        for (int i = 0; i < appointments.size(); i++){
            MyAppointments appointment = appointments.get(i);

            TableRow tr = new TableRow(MechanicAppointment.this);

            TextView c1 = new TextView(MechanicAppointment.this);
//            c1.setText(formatter.format(appointment.getDate()));

            TextView c2 = new TextView(MechanicAppointment.this);
//            c2.setText(appointment.getLocation());

            TextView c3 = new TextView(MechanicAppointment.this);
//            c3.setText(appointment.getTask());

            ImageView c4 = new ImageView(this);
            c4.setImageResource(R.drawable.close);
            c4.setTag(i);
            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int index = (int) view.getTag();
//                    appointments.remove(index);
                    mech.setAppointments(appointments);
                    dbRef.setValue(mech);

                    showAppointments(appointments);
                }
            });
            tr.addView(c1);
            tr.addView(c2);
            tr.addView(c3);
            tr.addView(c4);

            c1.setPadding(20,20,20,20);
            c2.setPadding(20,20,20,20);
            c3.setPadding(20,20,20,20);
            c4.setPadding(20,20,20,20);

            if(i % 2 == 1) {
                c1.setBackgroundColor(Color.LTGRAY);
                c2.setBackgroundColor(Color.LTGRAY);
                c3.setBackgroundColor(Color.LTGRAY);
                c4.setBackgroundColor(Color.LTGRAY);
            }
            tableLayout.addView(tr);
            tableLayout.requestLayout();
        }
    }
}