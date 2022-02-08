package com.example.projetlibre.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlibre.Controlle.DAOEmloyee;
import com.example.projetlibre.Model.Employer;
import com.example.projetlibre.Controlle.MyAdapter;
import com.example.projetlibre.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Employer> list;
    DAOEmloyee dao;
    String key =null;
    Button back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dao = new DAOEmloyee();
        LoadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        recyclerView = findViewById(R.id.userList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        //Intent intent_Chercher = new Intent(getApplicationContext(), ProfileEmployer.class);
       // recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }
    public void LoadData(){
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Employer emp = data.getValue(Employer.class);
                    emp.setKey(data.getKey());
                    list.add(emp);
                    key= data.getKey();
                }
              //  myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void onClick(View view) {

       // Toast.makeText(this, "ID", Toast.LENGTH_SHORT).show();

    }
    public void onBack(View view) {
        back = findViewById(R.id.btn_back);
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
}

    public void onbackk(View view) {
        back = findViewById(R.id.btn_back);
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }
}
