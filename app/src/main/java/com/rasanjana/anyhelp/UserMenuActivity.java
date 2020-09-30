package com.rasanjana.anyhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserMenuActivity extends AppCompatActivity {

    private static final String TAG = "UserMenuActivity";

    DatabaseReference dbRef;
    private List<Plumber> plumbers;
    private List<Teacher> teachers;
    //private List<Mechanic> mechanics;
    private List<Nurse> nurses;
    private String profession = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        plumbers = new ArrayList<>();
        teachers = new ArrayList<>();
       // mechanics = new ArrayList<>();
        nurses = new ArrayList<>();

//get buttons from appHome
        Intent intent = getIntent();
      profession = intent.getStringExtra(AppHomePage.profession);

            Log.i(TAG, "profession: "+ profession);

        final PlumberUserMenuAdapter plumberAdapter = new PlumberUserMenuAdapter(this);
        plumberAdapter.setPlumbers(plumbers);
        plumberAdapter.setOnItemClickListener(new PlumberUserMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Log.i(TAG, "onItemClicked: position = "+position);
            }
        });
        final TeacherUserMenuAdapter teacherAdapter = new TeacherUserMenuAdapter(this);
        teacherAdapter.setTeachers(teachers);
        teacherAdapter.setOnItemClickListener(new TeacherUserMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Log.i(TAG, "onItemClicked: position"+position);

            }
        });
//        final MechanicUserMenuAdapter mechanicAdapter = new MechanicUserMenuAdapter(this);
//        mechanicAdapter.setMechanics(mechanics);
//        mechanicAdapter.setOnItemClickListener(new MechanicUserMenuAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                Log.i(TAG, "onItemClicked: position"+position);
//            }
//        });
        final NurseUserMenuAdapter nurseAdapter = new NurseUserMenuAdapter(this);
        nurseAdapter.setNurses(nurses);
        nurseAdapter.setOnItemClickListener(new NurseUserMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Log.i(TAG, "onItemClicked: position"+position);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(UserMenuActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        switch (profession){
            case "teacher":
                recyclerView.setAdapter(teacherAdapter);


                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Teacher");
                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.i(TAG ,"Count = " + dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Log.i(TAG, "onDataChange: key = "+postSnapshot.getKey());
                            Teacher teacher = postSnapshot.getValue(Teacher.class);
                            teachers.add(teacher);
                        }

                        teacherAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;

            case "nurse":
                recyclerView.setAdapter(nurseAdapter);
                DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("Nurse");
                readRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.i(TAG ,"Count = " + dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Log.i(TAG, "onDataChange: key = "+postSnapshot.getKey());
                            Nurse nurse = postSnapshot.getValue(Nurse.class);
                            nurses.add(nurse);
                        }

                        nurseAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;

//            case "mechanic":
//                recyclerView.setAdapter(mechanicAdapter);
//                DatabaseReference readRef3 = FirebaseDatabase.getInstance().getReference().child("Mechanic");
//                readRef3.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Log.i(TAG ,"Count = " + dataSnapshot.getChildrenCount());
//                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                            Log.i(TAG, "onDataChange: key = "+postSnapshot.getKey());
//                            Mechanic mechanic = postSnapshot.getValue(Mechanic.class);
//                            mechanics.add(mechanic);
//                        }
//
//                        mechanicAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                break;
            case "plumber":
                recyclerView.setAdapter(plumberAdapter);
                DatabaseReference readRef4 = FirebaseDatabase.getInstance().getReference().child("Plumber");
                readRef4.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.i(TAG ,"Count = " + dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Log.i(TAG, "onDataChange: key = "+postSnapshot.getKey());
                            Plumber plumber = postSnapshot.getValue(Plumber.class);
                            plumbers.add(plumber);
                        }

                        plumberAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
        }
    }
}