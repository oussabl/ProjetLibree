package com.example.projetlibre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainImage extends AppCompatActivity {
String KEY ,nom,prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        KEY = bundle.getString("key");
        nom = bundle.getString("firstname");
        prenom = bundle.getString("lastname");


        Toast.makeText(this, "hello " +nom+prenom, Toast.LENGTH_SHORT).show();

    }


}