package com.example.projetlibre.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetlibre.Controlle.DAOEmloyee;
import com.example.projetlibre.Model.Employer;
import com.example.projetlibre.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_Login;
    FirebaseAuth mAuth;
    EditText edusername, edpassword;
    DAOEmloyee dao;
    String key = null;
    ArrayList<Employer> list;
    ArrayList<Employer> listt;
    Employer emp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dao = new DAOEmloyee();
        //LoadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        btn_Login = findViewById(R.id.btn_login);
        edpassword = findViewById(R.id.password_input);
        edusername = findViewById(R.id.username_input);
    }

    @SuppressLint("WrongConstant")
    public void OnLogin(View view) {
        String username = edusername.getText().toString().trim();
        String password = edpassword.getText().toString().trim();

        if (!(username.isEmpty() && password.isEmpty())){
                 listt = new ArrayList<Employer>();
            for (int i = 1; i < LoadData().size(); i++) {
               // emp = new Employer();
                listt = LoadData();
                  emp = listt.get(i);
        if (emp.getMission().equals("admin") && emp.getEmail().equals(username) && emp.getPassword().equals(password))
                {
                    Intent intent_Home = new Intent(this, Home.class);
                    intent_Home.putExtra("key", emp.getKey());
                    intent_Home.putExtra("firstname", emp.getFirstname());
                    intent_Home.putExtra("lastname", emp.getLastname());
                    intent_Home.putExtra("mission", emp.getMission());
                    intent_Home.putExtra("email", emp.getEmail());
                    startActivity(intent_Home);
                }
                 else if (  emp.getEmail().equals(username) &&
                            emp.getPassword().equals(password))
                {
                    Intent intent_Chercher = new Intent(this, ProfileEmployer.class);
                    intent_Chercher.putExtra("key", emp.getKey());
                    intent_Chercher.putExtra("firstname", emp.getFirstname());
                    intent_Chercher.putExtra("lastname", emp.getLastname());
                    intent_Chercher.putExtra("tel", emp.getTelephone());
                    intent_Chercher.putExtra("mission", emp.getMission());
                    intent_Chercher.putExtra("date_dep", emp.getDate_depart());
                    intent_Chercher.putExtra("date_fin", emp.getDate_fin());
                    intent_Chercher.putExtra("email", emp.getEmail());
                    intent_Chercher.putExtra("password", emp.getPassword());
                    startActivity(intent_Chercher);
                    cleanData();

                }
                 // else{}
            }

        }
        if (username.isEmpty()) {
            edusername.setError("Username is required");
            edusername.requestFocus();
        }
        if (password.isEmpty()) {
            edpassword.setError("Password is required");
            edpassword.requestFocus();
        }
    }

    public void cleanData(){
        edpassword.setText("");
        edusername.setText("");
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
                    key = data.getKey();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }


    }

