package com.example.projetlibre.View.Files;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetlibre.Model.PutPDF;
import com.example.projetlibre.R;
import com.example.projetlibre.View.ProfileEmployer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainPDF extends AppCompatActivity {
    Button btn;
    EditText etxt;
    EditText    mtxt;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String KEY,nom,prenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpdf);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        KEY = bundle.getString("key");
        nom = bundle.getString("firstname");
        prenom = bundle.getString("lastname");


         btn = findViewById(R.id.btn_valide);
         etxt = findViewById(R.id.idpdf);
         mtxt = findViewById(R.id.idtextt);

         storageReference = FirebaseStorage.getInstance().getReference();
         databaseReference= FirebaseDatabase.getInstance().getReference(PutPDF.class.getSimpleName());

        btn.setEnabled(false);

        etxt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectPDF();
        }
    });
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null){
            btn.setEnabled(true);
            etxt.setText(data.getDataString()
                    .substring(data.getDataString().lastIndexOf("/")+1));
            mtxt.setText(data.getDataString()
                    .substring(data.getDataString().lastIndexOf("/")+1));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadPDFFileFirebase(data.getData());
                }
            });
        }
    }

    private void uploadPDFFileFirebase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("File is loading ...");
        progressDialog.show();
        StorageReference reference = storageReference.child(mtxt.getText().toString()+""+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri = uriTask.getResult();
                        PutPDF putPDF = new PutPDF(mtxt.getText().toString(),uri.toString(),KEY,nom+" "+prenom);
                        databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);
                        Toast.makeText(MainPDF.this, "File Upload", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress =  (100.0 *snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setMessage("File Uploaded ..." +(int) progress+"%");
            }
        });
    }







    public void onbackk(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileEmployer.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}