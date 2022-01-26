package com.example.projetlibre.View.Messages;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetlibre.Controlle.DAOEmloyee;
import com.example.projetlibre.Controlle.DAOMessage;
import com.example.projetlibre.Model.Employer;
import com.example.projetlibre.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class SendEmailC extends AppCompatActivity  {
    EditText title,body;
    DAOEmloyee dao;
    DAOMessage daoM;
    ArrayList<Employer> list;
    Employer emp;
    List<String> listSpinner;
    Spinner mySpinner ;
    String userde;
    String key,nom,prenom,mission;

    Button messageEnvoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dao = new DAOEmloyee();
        daoM = new DAOMessage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        mySpinner = findViewById(R.id.spinner);
        listSpinner = new ArrayList<>();
        list = new ArrayList<>();

         Bundle bundle = new Bundle();
         bundle = getIntent().getExtras();
         key = bundle.getString("key");
         nom = bundle.getString("firstname");
         prenom = bundle.getString("lastname");
         mission = bundle.getString("mission");
      //  Toast.makeText(this, " missionnnnn  : "+mission, Toast.LENGTH_SHORT).show();

         title = findViewById(R.id.emailtitle);
         body = findViewById(R.id.emailBoddy);
         messageEnvoi = findViewById(R.id.btn_message_envoi);

        messageEnvoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetMessage();
            }
        });


        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    Employer emp = data.getValue(Employer.class);
                    //  list.add(emp);

                    ArrayAdapter<CharSequence>  myAdapter = ArrayAdapter.createFromResource(SendEmailC.this, R.array.listadmin, android.R.layout.simple_list_item_1);
                    myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mySpinner.setAdapter(myAdapter);


                    mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                             userde = parent.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void GetMessage(){
        TimeZone tz = TimeZone.getTimeZone("GMT+01:00");
        Calendar c = Calendar.getInstance(tz);
        @SuppressLint("DefaultLocale") String time = String.format("%02d" , c.get(Calendar.HOUR_OF_DAY))+":"+String.format("%02d" , c.get(Calendar.MINUTE))+":"+String.format("%02d" , c.get(Calendar.SECOND));

        Message message = new Message(nom+" "+prenom,userde,body.getText().toString(),title.getText().toString(),time);

        daoM.add(message);
        body.setText("");
        title.setText("");
    }
}

