package com.example.projetlibre.View.Messages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projetlibre.R;

public class ReadEmail extends AppCompatActivity {
String users,title,desc;

TextView User,titlee,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reademail);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        users = bundle.getString("users");
        title = bundle.getString("title");
        desc = bundle.getString("des");


        User = findViewById(R.id.readuser);
        titlee = findViewById(R.id.readtitle);
        des = findViewById(R.id.readdes);

        User.setText(users);
        titlee.setText(title);
        des.setText(desc);

    }


}