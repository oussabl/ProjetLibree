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

import com.example.projetlibre.R;
import com.example.projetlibre.View.Messages.ReadEmail;
import com.example.projetlibre.Model.Message;

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
        holder.firstName.setText(message.getUserSource());
        holder.title.setText(message.getTitle());
        holder.time.setText(message.getDatEnvoi());
        int pos=   holder.getAdapterPosition();
        holder.parentlayout
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Possitionid",String.valueOf(message.getUserDestination()));
                getPosition(message,pos);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstName, title, time;
        CardView parentlayout ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firrstname);
            title = itemView.findViewById(R.id.titlee);
            time = itemView.findViewById(R.id.idtime);
            parentlayout  = itemView.findViewById(R.id.cardView1);
        }

    }

    private void getPosition(Message emp, int po ){
    Intent intent_readEmail = new Intent(context, ReadEmail.class);
        //intent_readEmail.putExtra("key", emp.getKey());
        intent_readEmail.putExtra("users", emp.getUserSource());
        intent_readEmail.putExtra("title", emp.getTitle());
        intent_readEmail.putExtra("des", emp.getDescription());
    context.startActivity(intent_readEmail);
}
}

