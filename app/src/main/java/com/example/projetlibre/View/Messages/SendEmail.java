package com.example.projetlibre.View.Messages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetlibre.Controlle.DAOEmloyee;
import com.example.projetlibre.Model.Employer;
import com.example.projetlibre.R;
import com.example.projetlibre.View.Home;
import com.example.projetlibre.View.ProfileEmployer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SendEmail extends AppCompatActivity {
    EditText title,body;
    DAOEmloyee dao;
    ArrayList<Employer> listt;
    ArrayList<Employer> list;
    Employer emp;
     List<String> listSpinner;
    Spinner mySpinner ;
    String key,nom,prenom,mission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dao = new DAOEmloyee();


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
        Toast.makeText(this, " missionnnnn  : "+mission, Toast.LENGTH_SHORT).show();

        LoadData();


         title = findViewById(R.id.emailtitle);
         body = findViewById(R.id.emailBoddy);

        // LoadSpinner();

    }

    public ArrayList<Employer> LoadData(){

        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    Employer emp = data.getValue(Employer.class);
                    String key = data.getKey();
                    emp.setKey(key);
                    list.add(emp);

                if (!(mission.equals("admin") &&
                        emp.getMission().equals("admin")))
                {
                    //Dans cette partie justement on a ajouter a spinner les users qui avant de user admin est les auters n'est pas ajouter ..
                    listSpinner.add(emp.getLastname()+" "+emp.getFirstname());
                    System.out.println("******3");

                    ArrayAdapter<String> myAdapter=  new ArrayAdapter<String>(SendEmail.this,
                            android.R.layout.simple_list_item_1,listSpinner);
                    myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mySpinner.setAdapter(myAdapter);


                }
                else if (!mission.equals("admin")){
                    System.out.println("******4");

                    listSpinner.add("Belarbi Oussama");
                    listSpinner.add("Morsou Doae");
                    listSpinner.add("Mouhib Sanae");
                    ArrayAdapter<String> myAdapter=  new ArrayAdapter<String>(SendEmail.this,
                            android.R.layout.simple_list_item_1,listSpinner);
                    myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mySpinner.setAdapter(myAdapter);

                }

                }            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }


    public void LoadSpinner(){
        System.out.println("******2ZZ");

        listt = new ArrayList<Employer>();
        System.out.println("******2ZZ"+LoadData().size());

        for (int i = 1; i < LoadData().size(); i++) {
            // emp = new Employer();
            listt = LoadData();
            emp = listt.get(i);
            System.out.println("******2");


        }
    }

}