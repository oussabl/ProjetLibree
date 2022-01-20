package com.example.projetlibre.View.Files;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetlibre.Model.PutPDF;
import com.example.projetlibre.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity_list_pdf extends AppCompatActivity {

    ListView listusers , listimages;
    DatabaseReference databaseReference;
    List<PutPDF> uploadedPDF;
    List<PutPDF> uploadeduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_pdf);
        listimages = findViewById(R.id.listimage);
        listusers = findViewById(R.id.listuser);

        uploadedPDF = new ArrayList<>();
        uploadeduser = new ArrayList<>();
        retrievePDFFiles();

        listimages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PutPDF putPDF = uploadedPDF.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));
                startActivity(intent);
            }
        });
}


    private void retrievePDFFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("PutPDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    PutPDF putPDF = ds.getValue(PutPDF.class);
                    uploadedPDF.add(putPDF);
                    uploadeduser.add(putPDF);


                }
                String[] uploadName = new String[uploadedPDF.size()];
                String[] uploaduser = new String[uploadeduser.size()];
                for (int i = 0; i <uploadName.length ; i++) {
                    uploadName[i] = uploadedPDF.get(i).getName();
                    uploaduser[i] = uploadeduser.get(i).getUser();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,uploadName){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.WHITE);
                        textView.setTextSize(20);
                        textView.setBackgroundColor(Color.rgb(21, 102, 224));
                        return view;
                    }
                };
                ArrayAdapter<String> arrayAdapterr = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,uploaduser){
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
                listimages.setAdapter(arrayAdapter);
                listusers.setAdapter(arrayAdapterr);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onbackk(View view) {
    }

    public void checkData(View view) {
    }
}