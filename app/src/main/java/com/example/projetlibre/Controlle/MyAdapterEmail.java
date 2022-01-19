package com.example.projetlibre.Controlle;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlibre.Model.Message;
import com.example.projetlibre.R;
import com.example.projetlibre.View.Messages.ReadEmail;

import java.util.ArrayList;

public class MyAdapterEmail extends RecyclerView.Adapter<MyAdapterEmail.MyViewHolder> {

    Context context;

    ArrayList<Message> list;
    int positionn ;
    public MyAdapterEmail(Context context, ArrayList<Message> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itememail,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Message message = list.get(position);
        holder.firstName.setText(message.getNom());
        holder.lastName.setText(message.getPrenom());
        holder.email.setText(message.getEmail());
        int pos=   holder.getAdapterPosition();
        holder.parentlayout
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Possitionid",String.valueOf(pos));
                getPosition(message,pos);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstName, lastName, email;
        CardView parentlayout ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.tvfirstName);
            lastName = itemView.findViewById(R.id.tvlastName);
            email = itemView.findViewById(R.id.tvage);
            parentlayout  = itemView.findViewById(R.id.cardView);
        }

    }

    private void getPosition(Message emp, int po ){
    Intent intent_readEmail = new Intent(context, ReadEmail.class);
        intent_readEmail.putExtra("key", emp.getKey());
        intent_readEmail.putExtra("firstname", emp.getNom());
        intent_readEmail.putExtra("lastname", emp.getPrenom());
        intent_readEmail.putExtra("email", emp.getEmail());
    context.startActivity(intent_readEmail);
}
}

