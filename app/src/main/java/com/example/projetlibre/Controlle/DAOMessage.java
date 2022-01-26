package com.example.projetlibre.Controlle;

import com.example.projetlibre.Model.Conge;
import com.example.projetlibre.View.Messages.Message;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOMessage {
    private DatabaseReference databaseReference;
    public DAOMessage(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference= db.getReference(Message.class.getSimpleName());
    }

    public Task<Void> add(Message message){
        return databaseReference.push().setValue(message);
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
