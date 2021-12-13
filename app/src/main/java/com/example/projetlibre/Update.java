package com.example.projetlibre;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Update extends AppCompatActivity {
    Employer employer;
    private EditText edfname,edlname,edemail,edpassword,
            edcpassword,edtelephone,edmission,
            eddate_depart,eddate_fin;
    public Button update ,back,disable;
    String KEY;
    DAOEmloyee dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
            employer = new Employer();
            dao = new DAOEmloyee();
            update = findViewById(R.id.btn_update);
            disable= findViewById(R.id.btn_pdf);
            edfname= findViewById(R.id.idft);
            edlname= findViewById(R.id.idlt);
            edemail= findViewById(R.id.idem);
            edtelephone= findViewById(R.id.idtl);
            edpassword= findViewById(R.id.idpw);
            edcpassword= findViewById(R.id.cpassword);
            edmission= findViewById(R.id.idms);
            eddate_depart= findViewById(R.id.iddp);
            eddate_fin=  findViewById(R.id.id_datefin);

        OnDisableALL();

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
         KEY = bundle.getString("key");
        String nom = bundle.getString("firstname");
        String prenom = bundle.getString("lastname");
        String tel = bundle.getString("tel");
        String mission = bundle.getString("mission");
        String date_dep = bundle.getString("date_dep");
        String email = bundle.getString("email");
        String password = bundle.getString("password");
        String date_fin = bundle.getString("date_fin");
       // System.out.println("tele1"+tele);

       // Toast.makeText(Update.this, "tele |"+ tel, Toast.LENGTH_SHORT).show();
        //KEY.setText(_ID);
        edfname.setText(nom);
        edlname.setText(prenom);
        edtelephone.setText(tel);
        edmission.setText(mission);
        eddate_depart.setText(date_dep);
        eddate_fin.setText(date_fin);
        edemail.setText(email);
        edpassword.setText(password);
        edcpassword.setText(password);
        }
        
        private void cleandata(){
            edfname.setText("");
            edlname.setText("");
            edemail.setText("");
            edtelephone.setText("");
            edpassword.setText("");
            edcpassword.setText("");
            edmission.setText("");
            eddate_depart.setText("");
            eddate_fin.setText("");
        }

    public void OnUpdate(View view) {
        String firstname = edfname.getText().toString().trim();
        String lastname = edlname.getText().toString().trim();
        String email = edemail.getText().toString().trim();
        String telephone = edtelephone.getText().toString().trim();
        String password = edpassword.getText().toString().trim();
        String cpassword = edcpassword.getText().toString().trim();
        String mission = edmission.getText().toString().trim();
        String date_dep = eddate_depart.getText().toString().trim();
        String date_fin = eddate_fin.getText().toString().trim();
        System.out.println("tele"+telephone);

        employer.setFirstname(firstname);
        employer.setLastname(lastname);
        employer.setEmail(email);
        employer.setMission(mission);
        employer.setPassword(password);
        employer.setDate_depart(date_dep);
        employer.setDate_fin(date_fin);
        employer.setTelephone(telephone);

        if (firstname.isEmpty()){
            edfname.setError("Firstname is required");
            edfname.requestFocus();
            return;
        }
        if (lastname.isEmpty()){
            edlname.setError("Lastname is required");
            edlname.requestFocus();
            return;
        }
        if (telephone.length()<2){
            edtelephone.setError("Telephone is required Then 2 number");
            edtelephone.requestFocus();
            return;
        }
        if (email.isEmpty()){
            edemail.setError("Email is required");
            edemail.requestFocus();
            return;
        }
        if (mission.isEmpty()){
            edmission.setError("Please provide valid mission");
            edmission.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edemail.setError("Please provide valid email");
            edemail.requestFocus();
            return;
        }
        if (password.isEmpty() || password.length()< 1){
            edpassword.setError("Password is required , most than 1 cara");
            edpassword.requestFocus();
            return;
        }
        if (cpassword.isEmpty()){
            edcpassword.setError("Configuration Password is required  ");
            edcpassword.requestFocus();
            return;
        }
        if (!cpassword.equals(password)){
            edpassword.setError("check you your password the is not the same ");
            edpassword.requestFocus();
            return;
        }



        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("firstname",firstname);
        hashMap.put("lastname",lastname);
        hashMap.put("email",email);
        hashMap.put("telephone",telephone);
        hashMap.put("mission",mission);
        hashMap.put("password",password);
        // hashMap.put("cpassword",cpassword);
        dao.update(KEY,hashMap).addOnCompleteListener(suc ->{
            Toast.makeText(this, " Update done ", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(suc -> {
            Toast.makeText(this, " Update Field ", Toast.LENGTH_SHORT).show();
        });
    }    
    
    public void OnBack(View view) {
        back = findViewById(R.id.btn_back);
            Intent intent = new Intent(this, List.class);
            startActivity(intent);
    }
    public void OnDisableALL() {

        edfname.setEnabled(false);
        edlname.setEnabled(false);
        edemail.setEnabled(false);
        edpassword.setEnabled(false);
        edcpassword.setEnabled(false);
        edtelephone.setEnabled(false);
        edmission.setEnabled(false);
        eddate_depart.setEnabled(false);
        eddate_fin.setEnabled(false);
        update.setClickable(false);

    }
    public void disableNoActive(View view) {
        edfname.setEnabled(true);
        edlname.setEnabled(true);
        edemail.setEnabled(true);
        edpassword.setEnabled(true);
        edcpassword.setEnabled(true);
        edtelephone.setEnabled(true);
        edmission.setEnabled(true);
        eddate_depart.setEnabled(true);
        eddate_fin.setEnabled(true);
        update.setClickable(true);


    }
    public void OnDelete(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to delete this user");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
//                                Toast.makeText(Update.this,"You clicked yes Button",Toast.LENGTH_LONG).show();
                                dao.delete(KEY).addOnCompleteListener(suc ->{
                                }).addOnFailureListener(er -> {
                                });
                                Toast.makeText(Update.this,"user is deleted ",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Update.this, List.class);
                                startActivity(intent);

                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //   finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


        /*

*/

}