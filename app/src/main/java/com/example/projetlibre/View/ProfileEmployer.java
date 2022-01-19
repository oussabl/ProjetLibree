package com.example.projetlibre.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetlibre.R;
import com.example.projetlibre.View.Files.MainImage;
import com.example.projetlibre.View.Files.MainPDF;
import com.example.projetlibre.View.Messages.ListMessage;
import com.example.projetlibre.View.Messages.SendEmail;

public class ProfileEmployer extends AppCompatActivity {
EditText eddf,edfn,edlt,edms,eddp,edem,edtl,edpw;
    Button back ,pdf, donnee,mail;
    TextView textv ;
    String KEY,nom,prenom,mission,email ;
    AlertDialog  alertDialog;

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
        Intent intent = new Intent(ProfileEmployer.this, SendEmail.class);
        intent.putExtra("key", KEY);
        intent.putExtra("firstname", prenom);
        intent.putExtra("lastname", nom);
        intent.putExtra("mission", mission);
        intent.putExtra("email",email);

        startActivity(intent);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View Selectedchoix = getLayoutInflater().inflate(R.layout.selectedchoix, null);

        LinearLayout pdff = Selectedchoix.findViewById(R.id.btn_pdf);
        LinearLayout imgg = Selectedchoix.findViewById(R.id.btn_img);

        pdff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_PDF = new Intent(getApplicationContext(), MainPDF.class);
                intent_PDF.putExtra("key", KEY);
                intent_PDF.putExtra("firstname", prenom);
                intent_PDF.putExtra("lastname", nom);
                intent_PDF.putExtra("mission", mission);
                intent_PDF.putExtra("email",email);

                startActivity(intent_PDF);
            }
        });
        imgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_image = new Intent(getApplicationContext(), MainImage.class);
                intent_image.putExtra("key", KEY);
                intent_image.putExtra("firstname", prenom);
                intent_image.putExtra("lastname", nom);
                intent_image.putExtra("mission", mission);
                intent_image.putExtra("email",email);

                startActivity(intent_image);            }
        });
        builder.setView(Selectedchoix);
        alertDialog = builder.create();
        alertDialog.show();

      /*  Intent intent_PDF = new Intent(this, MainPDF.class);
        intent_PDF.putExtra("key", KEY);
        intent_PDF.putExtra("firstname", prenom);
        intent_PDF.putExtra("lastname", nom);
        //setResult(RESULT_OK, intent_PDF);
        startActivity(intent_PDF);*/
    }
}