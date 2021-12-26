package com.example.projetlibre;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileEmployer extends AppCompatActivity {
EditText eddf,edfn,edlt,edms,eddp,edem,edtl,edpw;
    Button back ,pdf, donnee,mail;
    TextView textv ;
    String KEY,nom,prenom,mission,email ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employer);

        edfn = findViewById(R.id.idft);
        edlt = findViewById(R.id.idlt);
        edms = findViewById(R.id.idms);
        eddp = findViewById(R.id.iddp);
        edem = findViewById(R.id.idem);
        edtl = findViewById(R.id.idtl);
        edpw = findViewById(R.id.idpw);
        eddf = findViewById(R.id.iddf);
        back = findViewById(R.id.btn_back);
        pdf = findViewById(R.id.btn_conge);
        mail = findViewById(R.id.btn_envoye);
        back = findViewById(R.id.btn_update);
        textv = findViewById(R.id.usernameEmployer);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
         KEY = bundle.getString("key");
         nom = bundle.getString("firstname");
         prenom = bundle.getString("lastname");
        String tel = bundle.getString("tel");
         mission = bundle.getString("mission");
        String date_dep = bundle.getString("date_dep");
        String date_fin = bundle.getString("date_fin");
         email = bundle.getString("email");
        String password = bundle.getString("password");

        System.out.println("shof"+KEY);
        edfn.setText(nom);
        edlt.setText(prenom);
        edtl.setText(tel);
        edms.setText(mission);
        eddp.setText(date_dep);
        eddf.setText(date_fin);
        edem.setText(email);
        edpw.setText(password);
        textv.setText("Welcome "+nom);


    }


    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    public void SendMail(View view) {
        Toast.makeText(this, " for send mail  ", Toast.LENGTH_SHORT).show();

    }
    public void OnBack(View view) {
        Toast.makeText(this, " We dont need it ", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, List.class);
        //  startActivity(intent);
    }
    public void Reserve(View view) {
        Intent intent_Reserve = new Intent(getApplicationContext(), Reserve_Conge.class);
        intent_Reserve.putExtra("key", KEY);
        intent_Reserve.putExtra("firstname", prenom);
        intent_Reserve.putExtra("lastname", nom);
        intent_Reserve.putExtra("mission", mission);
        intent_Reserve.putExtra("email",email);
        startActivity(intent_Reserve);

    }
    public void UploadDonnee(View view) {
        Toast.makeText(this, " Upload PDF ", Toast.LENGTH_SHORT).show();
    }
}