package com.example.my;


import static android.media.CamcorderProfile.get;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class retrieveimageAdapter extends RecyclerView.Adapter<retrieveimageAdapter.myviewholder>  {
    //retrieveimagemodel data[];
    ArrayList<retrieveimagemodel> data;
    //ArrayList<retrieveimagemodel> backup;
    Context context;


    public retrieveimageAdapter(ArrayList<retrieveimagemodel> data, Context context) {

        this.data = data;
        this.context = context;
//         backup = new ArrayList<>(data);


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        // final retrieveimagemodel temp=data[position];
        final retrieveimagemodel temp = data.get(position);

       // holder.t1.setText(data.get(position).getName());
        //holder.t2.setText(data.get(position).getModel());
       // holder.t3.setText(data.get(position).getEhrate());
        //holder.t4.setText(data.get(position).getLocation());
        //  holder.t5.setText(data.get(position).getEmail());
        //Glide.with(holder.t1.getContext()).load("http://192.168.1.5/API/upload/" + data.get(position).getImage()).into(holder.img);
          holder.t1.setText(data.get(position).getName());
         holder.t2.setText(data.get(position).getModel());
         holder.t3.setText(data.get(position).getEhrate());
         holder.t4.setText(data.get(position).getLocation());
//         holder.t5.setText(data[position].getEmail());
          Glide.with(holder.t1.getContext()).load("http://192.168.43.10/API/upload/"+ data.get(position).getImage()).into(holder.img);
        //  Glide.with(holder.t1.getContext()).load("http://192.168.43.10/API/upload/"+data[position].getImage()).into(holder.img);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, selectmachinery.class);
                intent.putExtra("imagename", temp.getImage());

                intent.putExtra("showname", temp.getName());
                intent.putExtra("showmodel", temp.getModel());
                intent.putExtra("showspecs", temp.getSpecs());
                intent.putExtra("showrate", temp.getEhrate());
                intent.putExtra("showlocation", temp.getLocation());
                intent.putExtra("email", temp.getEmail());
                intent.putExtra("usernum", temp.getPhonenumber());


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView t1, t2, t3, t4, t5;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
            // t5=itemView.findViewById(R.id.t5);
        }
    }



}