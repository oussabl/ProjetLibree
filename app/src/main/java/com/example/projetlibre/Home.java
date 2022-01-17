package com.example.projetlibre;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    LinearLayout lchercher ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lchercher = findViewById(R.id.lchercher);

    }

    public void add_employer(View view) {
        Intent intent = new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }

    public void onlistUsers(View view) {
        Intent intent = new Intent(getApplicationContext(),List.class);
        startActivity(intent);
    }

    public void aaaa(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity_list_pdf.class);
        startActivity(intent);
    }



public void dialog(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
        final EditText input = new EditText(getApplicationContext());
        input.setHint("Tapez le nom de client ! ");
        alertDialogBuilder.setView(input);
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                String  nom = input.getText().toString();
                                Toast.makeText(Home.this,"You clicked yes button -> "+nom,Toast.LENGTH_LONG).show();

                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}


/*
    //Methode de chercher ...
    cheEmp = findViewById(R.id.buttonCherche);
        cheEmp.setOnClickListener(view -> {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog);
        alert.setTitle("Rechercher");
        alert.setTitle("Tapez your code");

        final EditText input = new EditText(getApplicationContext());
        alert.setView(input);
        alert.setPositiveButton("OK", (dialog, which) -> {
            int _ID = Integer.parseInt(input.getText().toString());
            System.out.println(_ID);
            Employe emp = getEmplByID(_ID);
            if (emp != null) {
                Intent intent_Chercher = new Intent(getApplicationContext(), RechercheEmploye.class);
                intent_Chercher.putExtra("ID", emp.getId());
                intent_Chercher.putExtra("nom", emp.getNom());
                intent_Chercher.putExtra("prenom", emp.getPrenom());
                intent_Chercher.putExtra("tel", emp.getTel());
                intent_Chercher.putExtra("mission", emp.getMission());
                intent_Chercher.putExtra("date_dep", emp.getDate_depart());
                intent_Chercher.putExtra("date_arr", emp.getDate_arrive());
                startActivity(intent_Chercher);
            } else {
                Toast.makeText(MainActivity.this, "Employed of " + _ID + " is not existed", Toast.LENGTH_SHORT).show();
            }

        });
        alert.setNegativeButton("Cancel", (dialog, which) -> {

        });
        alert.show();
    });

*/
