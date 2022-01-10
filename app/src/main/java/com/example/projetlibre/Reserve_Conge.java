package com.example.projetlibre;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Reserve_Conge extends AppCompatActivity {
    AlertDialog alertDialog;
    int key_date = 0;
    TextView date_con_depp, date_con_finn , TextConge;
    DAOConge daoconge;
    Conge conge,cg;
    String KEY, nom, prenom, mission, email;
    ArrayList<Conge> ListConge;
    ArrayList<Conge> listt;
    Button checkConge;
    String yeard,moind,dayd,yearf,moinf,dayf;
    String dateBeforeString ,dateAfterString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        daoconge = new DAOConge();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_conge);
        date_con_finn = findViewById(R.id.date_con_fin);
        date_con_depp = findViewById(R.id.date_con_dep);
        checkConge = findViewById(R.id.checkConge);
        TextConge = findViewById(R.id.TextConge);
        LoadData();
        ListConge = new ArrayList<>();


        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        KEY = bundle.getString("key");
        nom = bundle.getString("firstname");
        prenom = bundle.getString("lastname");
        mission = bundle.getString("mission");
        email = bundle.getString("email");


       /* checkConge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("11");
                CountConge();
                Toast.makeText(Reserve_Conge.this, "Done1 -> "+LoadData().size(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    @SuppressLint("SetTextI18n")
    public void Calclick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View VCalPopDate = getLayoutInflater().inflate(R.layout.popcal, null);
        CalendarView cvDate = VCalPopDate.findViewById(R.id.calendarV);
        Button valideCal = VCalPopDate.findViewById(R.id.btn_valide);
        cvDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view1, int year, int month, int dayOfMonth) {
                System.out.println("year" + year);
                System.out.println("year" + month);
                System.out.println("year" + dayOfMonth);
                if (key_date == 0) {

                    date_con_depp.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    key_date++;
                } else if (key_date == 1) {
                    date_con_finn.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void onbackk(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileEmployer.class);
        startActivity(intent);
    }
    //Funtion pour ajouter conge
    public void AjouterConge() {
        String date_dep = (String) date_con_depp.getText();
        String date_fin = (String) date_con_finn.getText();
        Conge conge = new Conge(KEY, prenom, nom, date_dep, date_fin);
        daoconge.add(conge);
        Toast.makeText(this, "Request is DONE", Toast.LENGTH_SHORT).show();
    }

    public void Validation(View view) {
        AjouterConge();
    }

    // The problem is the this function he dosent return any value
    public ArrayList<Conge> LoadData() {
        daoconge.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Conge congeEmp =  data.getValue(Conge.class);
                    String key = data.getKey();
                    congeEmp.setKeyPere(key);
                    ListConge.add(congeEmp);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Reserve_Conge.this, "Problem From Database", Toast.LENGTH_SHORT).show();
            }
        });
        return ListConge;
    }

    public void CountConge() throws ParseException {
        listt = new ArrayList<Conge>();
        for (int i = 0; i < LoadData().size(); i++) {
            listt = LoadData();
              conge = listt.get(i);
            if (conge.getKEY().equalsIgnoreCase(KEY)){
                Toast.makeText(this, " this done", Toast.LENGTH_SHORT).show();
                String dated = conge.getDate_depart_conge();
                String datef = conge.getDate_fin_conge();
                    DateConge(dated,datef);
                    // if you need met pause in you program
              //      Thread.sleep(10000);
            }
            else{
                Toast.makeText(this, " not  done", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onCheck(View view) throws ParseException {
        CountConge();
        Toast.makeText(Reserve_Conge.this, "Done1 -> "+LoadData().size(), Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("WrongConstant")
    public long DateConge(String depart, String fin) throws ParseException {
        final String SEPARATEUR = "/";
        String ddepart[] = depart.split(SEPARATEUR);

        for (int a = 0; a < ddepart.length; a++) {
            System.out.println(ddepart[a]);
            dayd = ddepart[0];
            moind = ddepart[1];
            yeard   = ddepart[2];
        }
        String dfin[] = fin.split(SEPARATEUR);

        for (int j = 0; j < dfin.length; j++) {
            System.out.println(dfin[j]);
            dayf = dfin[0];
            moinf = dfin[1];
            yearf  = dfin[2];
        }
        String departtt = ""+dayd+"/"+moind+"/"+yeard+"";
        String arrivee = ""+dayf+"/"+moinf+"/"+yearf+"";
        System.out.println("departtt : "+departtt);
        System.out.println("arrivee :"+arrivee);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            SimpleDateFormat ffff = new SimpleDateFormat("dd/MM/yyyy");
            Date dateAvantt = ffff.parse(departtt);
            Date dateAprest = ffff.parse(arrivee);
            long difff = dateAprest.getTime() - dateAvantt.getTime();
            int ress =  (int)(difff / (1000*60*60*24));
            String resulta = String.valueOf(ress);
            TextConge.setText(resulta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}