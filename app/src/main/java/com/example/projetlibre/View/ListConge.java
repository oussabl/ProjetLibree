package com.example.projetlibre.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetlibre.Model.Conge;
import com.example.projetlibre.Model.PutPDF;
import com.example.projetlibre.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListConge extends AppCompatActivity {


    ListView listusers;
    ListView listdate;
    ListView listjour ,listdatearr;
    DatabaseReference databaseReference;

    List<Conge> lists_Congee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_conge);

        listusers = findViewById(R.id.listuserr);
        listdate = findViewById(R.id.listdate);
        listdatearr = findViewById(R.id.listdatearr);
        listjour = findViewById(R.id.listtjour);

        lists_Congee = new ArrayList<>();

        retrievePDFFiles();

    }

    private void retrievePDFFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Conge");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Conge conge = ds.getValue(Conge.class);
                    lists_Congee.add(conge);
                  //  uploadeduser.add(conge);
                 //   uploadeddate.add(conge);

                }
                String[] colnbr = new String[lists_Congee.size()];
                String[] coluser = new String[lists_Congee.size()];
                String[] coldate = new String[lists_Congee.size()];
                String[] coldatearr = new String[lists_Congee.size()];

                for (int i = 0; i <colnbr.length ; i++) {
                    colnbr[i] = String.valueOf( lists_Congee.get(i).getTOTALCONGE());
                    coluser[i] = lists_Congee.get(i).getUsers();
                    coldate[i] = lists_Congee.get(i).getDate_depart_conge();
                    coldatearr[i] = lists_Congee.get(i).getDate_fin_conge();
                }
                ArrayAdapter<String> arrayAdapterdate = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,coldate){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.rgb(21, 102, 224));
                        textView.setTextSize(14);
                        textView.setClickable(false);
                        return view;
                    }
                };



                ArrayAdapter<String> arrayAdapterdatearr = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,coldatearr){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.rgb(21, 102, 224));
                        textView.setTextSize(14);
                        textView.setClickable(false);
                        return view;
                    }
                };


                ArrayAdapter<String> arrayAdapterUser = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,coluser){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.rgb(21, 102, 224));
                        textView.setTextSize(14);
                        textView.setClickable(false);
                        return view;
                    }
                };
                ArrayAdapter<String> arrayAdapternbr = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,colnbr){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.rgb(21, 102, 224));
                        textView.setTextSize(14);
                     //   textView.setGravity(Gravity.CENTER);
                        textView.setClickable(false);
                        return view;
                    }
                };
                listusers.setAdapter(arrayAdapterUser);
                listdate.setAdapter(arrayAdapterdate);
                listjour.setAdapter(arrayAdapternbr);
                listdatearr.setAdapter(arrayAdapterdatearr);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}