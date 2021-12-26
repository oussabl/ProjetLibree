package com.example.projetlibre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class Reserve_Conge extends AppCompatActivity {
    AlertDialog  alertDialog;
    int key_date =0;
    TextView date_con_depp,date_con_finn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_conge);
        date_con_finn=findViewById(R.id.date_con_fin);
        date_con_depp=findViewById(R.id.date_con_dep);


        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        String KEY = bundle.getString("key");
        String nom = bundle.getString("firstname");
        String prenom = bundle.getString("lastname");
        String mission = bundle.getString("mission");
        String email = bundle.getString("email");

    }
    @SuppressLint("SetTextI18n")
    public void Calclick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View VCalPopDate = getLayoutInflater().inflate(R.layout.popcal,null);
        CalendarView cvDate = VCalPopDate.findViewById(R.id.calendarV);
        Button  valideCal = VCalPopDate.findViewById(R.id.btn_valide);
        cvDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view1, int year, int month, int dayOfMonth) {
                System.out.println("year" + year);
                System.out.println("year" + month);
                System.out.println("year" + dayOfMonth);
                if (key_date == 0) {
                    date_con_finn.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    key_date++;
                } else if (key_date == 1) {
                    date_con_depp.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    key_date--;
                }
            }
        });

        valideCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        builder.setView(VCalPopDate);
        alertDialog=builder.create();
        alertDialog.show();
    }


    public void onbackk(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileEmployer.class);
        startActivity(intent);
    }
}