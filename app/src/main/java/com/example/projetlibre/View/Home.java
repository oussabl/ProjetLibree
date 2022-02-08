package com.example.projetlibre.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetlibre.R;
import com.example.projetlibre.View.Files.MainActivity_list_pdf;
import com.example.projetlibre.View.Messages.ListMessage;
import com.example.projetlibre.View.Messages.SendEmail;
import com.example.projetlibre.View.ListConge;
import com.example.projetlibre.View.Messages.SendEmailC;

public class Home extends AppCompatActivity {

    LinearLayout lchercher ,
            add_employer,
            onlistUsers,
            List_PDF,
            List_Conge,
            dialogCherche,
            List_Message,
            mailversclient;
    String KEY,nom,prenom,mission,email;
    AlertDialog  alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        add_employer = findViewById(R.id.btn_addd);
        onlistUsers = findViewById(R.id.btn_listt);
        List_Message = findViewById(R.id.btn_list_messagee);

        lchercher = findViewById(R.id.lchercher);
        List_PDF = findViewById(R.id.btn_list_pdff);
        mailversclient = findViewById(R.id.btn_list_read);

        List_Conge = findViewById(R.id.btn_list_congee);
        dialogCherche = findViewById(R.id.deletee);

        //Recuperation
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        KEY = bundle.getString("key");
        nom = bundle.getString("firstname");
        prenom = bundle.getString("lastname");
        mission = bundle.getString("mission");
        email = bundle.getString("email");

        Toast.makeText(this, "voila mission" +mission +" ****" +nom, Toast.LENGTH_SHORT).show();


        add_employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_employer(v);
            }
        });
        onlistUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlistUsers(v);
            }
        });
        List_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List_Message(v);
            }
        });

        List_PDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List_PDF(v);
            }
        });
        lchercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lchercher(v);
            }
        });

        mailversclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailversclient(v);
            }
        });
        List_Conge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List_Conge(v);
            }
        });





    }

    public void add_employer(View view) {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);

    }
    public void onlistUsers(View view) {
       Intent intent = new Intent(getApplicationContext(), List.class);
       startActivity(intent);
        //Toast.makeText(this, "From List Users", Toast.LENGTH_SHORT).show();

    }
    public void List_PDF(View view) {
            Intent intent = new Intent(getApplicationContext(), MainActivity_list_pdf.class);
            startActivity(intent);
            //Toast.makeText(this, "From List PDF", Toast.LENGTH_SHORT).show();

        }
    public void List_Conge(View view) {
                Intent intent = new Intent(this, ListConge.class);
                startActivity(intent);
           // Toast.makeText(this, "From ListConge", Toast.LENGTH_SHORT).show();

        }
    public void lchercher(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
        final EditText input = new EditText(getApplicationContext());
        input.setHint("nom of the client you search  ! ");
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
    public void mailversclient(View view) {

        Intent intentt = new Intent(this, SendEmail.class);
        intentt.putExtra("key", KEY);
        intentt.putExtra("firstname", prenom);
        intentt.putExtra("lastname", nom);
        intentt.putExtra("mission", mission);
        intentt.putExtra("email",email);
        startActivity(intentt);

        Toast.makeText(this, " Email Vers Client", Toast.LENGTH_SHORT).show();

    }
    public void List_Message(View view) {
       /* Intent intent = new Intent(this, ListMessage.class);
        startActivity(intent);*/
       // Toast.makeText(this, "From ListMessage", Toast.LENGTH_SHORT).show();
        Message(view);
    }



    public void Message(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View Selectedchoix = getLayoutInflater().inflate(R.layout.message, null);

        LinearLayout list = Selectedchoix.findViewById(R.id.messagelist);
        LinearLayout send = Selectedchoix.findViewById(R.id.messagesend);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ListMessage.class);
                intent.putExtra("key", KEY);
                intent.putExtra("firstname", prenom);
                intent.putExtra("lastname", nom);
                intent.putExtra("mission", mission);
                intent.putExtra("email",email);

                startActivity(intent);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_image = new Intent(getApplicationContext(), SendEmail.class);
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


