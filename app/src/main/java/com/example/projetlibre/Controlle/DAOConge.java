package com.example.projetlibre.Controlle;

import com.example.projetlibre.Model.Conge;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOConge {
    private DatabaseReference databaseReference;
    public DAOConge(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference= db.getReference(Conge.class.getSimpleName());
    }

    public Task<Void> add(Conge conge){
        return databaseReference.push().setValue(conge);
    }
    public Task<Void> update(String key , HashMap<String , Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> delete(String key ){
        return databaseReference.child(key).removeValue();
    }

    public Query get(){
        return databaseReference.orderByKey();
    }

}
