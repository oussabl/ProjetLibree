package com.example.projetlibre.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetlibre.Controlle.DAOEmloyee;
import com.example.projetlibre.Model.Employer;
import com.example.projetlibre.R;

public class Register extends AppCompatActivity{

    Employer employer;
    private EditText edfname,edlname,edemail,edpassword,
                     edcpassword,edtelephone,edmission,
                     eddate_depart,eddate_fin;
    AlertDialog  alertDialog;
    protected Button add_user,back ;
    DAOEmloyee dao;
    int key_date =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        employer = new Employer();
        dao = new DAOEmloyee();
        add_user = findViewById(R.id.btn_add_user);
        edfname= findViewById(R.id.idft);
        edlname= findViewById(R.id.idlt);
        edemail= findViewById(R.id.idem);
        edtelephone= findViewById(R.id.idtl);
        edpassword= findViewById(R.id.idpw);
        edcpassword= findViewById(R.id.cpassword);
        edmission= findViewById(R.id.idms);
        eddate_depart= findViewById(R.id.date_dep);
        eddate_fin=  findViewById(R.id.id_datefin);

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        });


        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        OnaddUsers(view);
                };
            });
           }

    private void OnaddUsers(View v) {
        String firstname = edfname.getText().toString().trim();
        String lastname = edlname.getText().toString().trim();
        String email = edemail.getText().toString().trim();
        String telephone = edtelephone.getText().toString().trim();
        String password = edpassword.getText().toString().trim();
        String cpassword = edcpassword.getText().toString().trim();
        String mission = edmission.getText().toString().trim();
        String date_dep = eddate_depart.getText().toString().trim();
        String date_fin = eddate_fin.getText().toString().trim();

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

        employer.setFirstname(firstname);
        employer.setLastname(lastname);
        employer.setEmail(email);
        employer.setMission(mission);
        employer.setPassword(password);
        employer.setDate_depart(date_dep);
        employer.setDate_fin(date_fin);
        employer.setTelephone(telephone);

        // dont need it we use id here
      //  reff.child(String.valueOf(maxid+1)).setValue(employer );

        //  Add user
     dao.add(employer).addOnCompleteListener(suc ->{
            Toast.makeText(this, " THIS"+firstname+','+lastname+','+telephone+','+date_dep+" \n DATA INSERT Succesfully ", Toast.LENGTH_SHORT).show();
             cleandata();
        }).addOnFailureListener(suc -> {
            Toast.makeText(this, " THISDATA INSERT Field ", Toast.LENGTH_SHORT).show();
        });
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
                    eddate_depart.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    key_date++;
                } else if (key_date == 1) {
                    eddate_fin.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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


    /*
    @SuppressLint("SetTextI18n")
    public void Calclick1(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        View VCalPopDate1 = getLayoutInflater().inflate(R.layout.popcal,null);
        CalendarView cvDate1 = VCalPopDate1.findViewById(R.id.calendarV);
        Button  valideCal1 = VCalPopDate1.findViewById(R.id.btn_valide);
        cvDate1.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            System.out.println("year"+year);
            System.out.println("year"+month);
            System.out.println("year"+dayOfMonth);
            eddate_fin.setText(dayOfMonth+"/"+(month+1)+"/"+year);

        });
        valideCal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        builder1.setView(VCalPopDate1);
        alertDialog=builder1.create();
        alertDialog.show();
    }
*/
}