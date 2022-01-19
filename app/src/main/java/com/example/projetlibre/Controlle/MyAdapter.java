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

import com.example.projetlibre.Model.Employer;
import com.example.projetlibre.R;
import com.example.projetlibre.View.Update;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Employer> list;
    int positionn ;
    public MyAdapter(Context context, ArrayList<Employer> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Employer emp = list.get(position);
        holder.firstName.setText(emp.getFirstname());
        holder.lastName.setText(emp.getLastname());
        holder.email.setText(emp.getEmail());
        int pos=   holder.getAdapterPosition();
        holder.parentlayout
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Possitionid",String.valueOf(pos));
                getPosition(emp,pos);

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

    private void getPosition(Employer emp, int po ){
    Intent intent_Chercher = new Intent(context, Update.class);
    intent_Chercher.putExtra("key", emp.getKey());
    intent_Chercher.putExtra("firstname", emp.getFirstname());
    intent_Chercher.putExtra("lastname", emp.getLastname());
    intent_Chercher.putExtra("tel", emp.getTelephone());
    intent_Chercher.putExtra("mission", emp.getMission());
    intent_Chercher.putExtra("date_dep", emp.getDate_depart());
    intent_Chercher.putExtra("date_fin", emp.getDate_fin());
    intent_Chercher.putExtra("email", emp.getEmail());
    intent_Chercher.putExtra("password", emp.getPassword());

    context.startActivity(intent_Chercher);
}
}

