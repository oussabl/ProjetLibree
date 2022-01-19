package com.example.projetlibre.View.Files;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetlibre.Model.PutImage;
import com.example.projetlibre.Model.PutPDF;
import com.example.projetlibre.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class MainImage extends AppCompatActivity {
    String KEY ,nom,prenom;
    Button btnchoose ,btnipload;
    ImageView imageView;
    EditText txtimage;
    private Uri filePath;
    private FirebaseStorage storage;
    DatabaseReference databaseReference;

    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image);


        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        KEY = bundle.getString("key");
        nom = bundle.getString("firstname");
        prenom = bundle.getString("lastname");

        btnchoose = findViewById(R.id.btn_img_cp);
        btnipload = findViewById(R.id.btn_img_up);
        imageView = findViewById(R.id.imagecapture);
        txtimage = findViewById(R.id.nomimage);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference(PutImage.class.getSimpleName());




        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),12);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();

            try {
                // The  parameter supprime
               // uploadImageFileFirebase(data.getData());
                btnipload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        uploadImageFileFirebase(filePath);
                    }
                });
                 Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                 imageView.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


  /*  public void onbackk(View view) {
        Intent intent_PDF = new Intent(getApplicationContext(), MainPDF.class);
        startActivity(intent_PDF);
    }*/
}

    private void uploadImageFileFirebase(Uri data) {
        if (filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);

            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference reference = storageReference.child("images/"+txtimage.getText().toString());
            reference.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri = uriTask.getResult();
                        PutPDF putPDF = new PutPDF(txtimage.getText().toString(),uri.toString(),KEY,nom+" "+prenom);
                        databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);

                        Toast.makeText(MainImage.this, "Image Uploaded ...", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progres = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressDialog.setMessage("Uploaded "+(int)progres+"%");
            }
        });
        }
    }


    public void onbackk(View view) {
    }
}