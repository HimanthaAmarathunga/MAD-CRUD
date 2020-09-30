package com.rasanjana.anyhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CareerViewAddedAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_view_added_appointment);

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TableLayout tableLayout = findViewById(R.id.careerTable);
        tableLayout.setStretchAllColumns(true);
        tableLayout.bringToFront();

        TableRow header =  new TableRow(this);
        TextView h1 = new TextView(this);
        h1.setText("Date");
        TextView h2 = new TextView(this);
        h2.setText("Location");
        TextView h3 = new TextView(this);
        h3.setText("Task");
        TextView h4 = new TextView(this);
        h4.setText("");
        TextView h5 = new TextView(this);
        h5.setText("");

        header.addView(h1);
        header.addView(h2);
        header.addView(h3);
        header.addView(h4);
        header.addView(h5);

        h1.setBackgroundColor(getResources().getColor(R.color.tableHeader));
        h2.setBackgroundColor(getResources().getColor(R.color.tableHeader));
        h3.setBackgroundColor(getResources().getColor(R.color.tableHeader));
        h4.setBackgroundColor(getResources().getColor(R.color.tableHeader));
        h5.setBackgroundColor(getResources().getColor(R.color.tableHeader));

        h1.setPadding(20, 20 , 20, 20);
        h2.setPadding(20, 20 , 20, 20);
        h3.setPadding(20, 20 , 20, 20);
        h4.setPadding(20, 20 , 20, 20);
        h5.setPadding(20, 20 , 20, 20);

        tableLayout.addView(header);

        /*for(int i = 1; i < 11; i++){
            TableRow tr =  new TableRow(this);
            TextView c1 = new TextView(this);
            c1.setText("Name "+i);
            TextView c2 = new TextView(this);
            c2.setText(String.valueOf(i*10));
            TextView c3 = new TextView(this);
            c3.setText(String.valueOf(2*i));
*/
//row1
        TableRow tr = new TableRow(this);
        TextView c1 = new TextView(this);
        c1.setText("07/25");
        TextView c2 = new TextView(this);
        c2.setText(String.valueOf("Gampaha"));
        TextView c3 = new TextView(this);
        c3.setText(String.valueOf("Repair"));
        TextView c4 = new TextView(this);
        c4.setText(String.valueOf(""));
        TextView c5 = new TextView(this);
        c5.setText(String.valueOf(""));

        tr.addView(c1);
        tr.addView(c2);
        tr.addView(c3);
        tr.addView(c4);
        tr.addView(c5);

        c1.setPadding(20, 20 , 10, 20);
        c2.setPadding(20, 20 , 10, 20);
        c3.setPadding(20, 20 , 10, 20);
        c4.setPadding(20, 20 , 10, 20);
        c5.setPadding(20, 20 , 10, 20);
        //row2
        TableRow tr1 = new TableRow(this);
        TextView c11 = new TextView(this);
        c11.setText("07/26");
        TextView c22 = new TextView(this);
        c22.setText(String.valueOf("Veyangoda"));
        TextView c33 = new TextView(this);
        c33.setText(String.valueOf("Repair"));
        TextView c44 = new TextView(this);
        c44.setText(String.valueOf(""));
        TextView c55 = new TextView(this);
        c55.setText(String.valueOf(""));

        tr1.addView(c11);
        tr1.addView(c22);
        tr1.addView(c33);
        tr1.addView(c44);
        tr1.addView(c55);

        c11.setPadding(20, 20 , 20, 20);
        c22.setPadding(20, 20 , 20, 20);
        c33.setPadding(20, 20 , 20, 20);
        c44.setPadding(20, 20 , 20, 20);
        c55.setPadding(20, 20 , 20, 20);

        //row2
        TableRow tr11 = new TableRow(this);
        TextView c111 = new TextView(this);
        c111.setText("07/27");
        TextView c222 = new TextView(this);
        c222.setText(String.valueOf("Veyangoda"));
        TextView c333 = new TextView(this);
        c333.setText(String.valueOf("Repair"));
        ImageView c444 = new ImageView(this);
        c444.setImageResource(R.drawable.edit);
        ImageView c555 = new ImageView(this);
        c555.setImageResource(R.drawable.close);

        tr11.addView(c111);
        tr11.addView(c222);
        tr11.addView(c333);
        tr11.addView(c444);
        tr11.addView(c555);

        c111.setPadding(20, 20 , 20, 20);
        c222.setPadding(20, 20 , 20, 20);
        c333.setPadding(20, 20 , 20, 20);
        c444.setPadding(20, 20 , 20, 20);
        c555.setPadding(20, 20 , 20, 20);


        tr11.setBackgroundColor(getResources().getColor(R.color.tableRaw));




        tableLayout.addView(tr);
        tableLayout.addView(tr1);
        tableLayout.addView(tr11);
        tableLayout.requestLayout();
    }
}