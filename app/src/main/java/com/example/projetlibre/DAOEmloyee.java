package com.example.projetlibre;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmloyee {
    private DatabaseReference databaseReference;
    public DAOEmloyee(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        databaseReference= db.getReference(Employer.class.getSimpleName());
    }
    public Task<Void> add(Employer emp){
        return databaseReference.push().setValue(emp);
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
