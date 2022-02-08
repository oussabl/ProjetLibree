package com.example.projetlibre.View.Messages;

import android.os.Bundle;

import com.example.projetlibre.Controlle.DAOMessage;
import com.example.projetlibre.Controlle.MyAdapterEmail;
import com.example.projetlibre.Model.Message;
import com.example.projetlibre.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListMessage extends AppCompatActivity {

    String users ,nom,prenom, mission,email;
    RecyclerView recyclerView;
    MyAdapterEmail myAdapter;
    DAOMessage daoM;
    String key =null;
    ArrayList<Message> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        daoM = new DAOMessage();
        LoadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_message);
        recyclerView = findViewById(R.id.listMessage);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
      //  KEY = bundle.getString("key");
        nom = bundle.getString("firstname");
        prenom = bundle.getString("lastname");
        mission = bundle.getString("mission");
        email = bundle.getString("email");



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapterEmail(this,list);
        recyclerView.setAdapter(myAdapter);

        //Intent intent_Chercher = new Inte nt(getApplicationContext(), ProfileEmployer.class);
        // recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }
    public void LoadData(){
        daoM.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Message msg = data.getValue(Message.class);
                    //if()
                 //   msg.setKey(data.getKey());
                    Log.v("Possitionidd",String.valueOf(data.getKey()));
                if (msg.getUserSource().equalsIgnoreCase(nom+" "+prenom) || msg.getUserDestination().equalsIgnoreCase(nom+" "+prenom) ){
                    list.add(msg);
                    key= data.getKey();
                }
                else {
                    Toast.makeText(ListMessage.this, "Your mailbox is empty ", Toast.LENGTH_SHORT).show();
                }
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}