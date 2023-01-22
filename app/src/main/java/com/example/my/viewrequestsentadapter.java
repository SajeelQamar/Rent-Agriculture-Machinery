package com.example.my;


import static android.media.CamcorderProfile.get;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class viewrequestsentadapter extends RecyclerView.Adapter<viewrequestsentadapter.myviewholder>
{
    viewrequestsentmodel data[];

    Context context;




    public viewrequestsentadapter(viewrequestsentmodel[] data, Context context) {

        this.data = data;
        this.context=context;


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewrequestsentrequestitems,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final viewrequestsentmodel temp=data[position];
        holder.noreqsent.setText(data[position].getNorequestsent());
        holder.eraseuser.setText(data[position].getEraseusername());
        holder.erasemach.setText(data[position].getErasemacname());
        holder.requesthasbeensent.setText(data[position].getRequesthasbeensent());
        holder.acceptreject.setText(data[position].getAccept());
        holder.recemail.setText(data[position].getRecemail());
        holder.machinename.setText(data[position].getMachinename());






    }

    @Override
    public int getItemCount() {
        return data.length;
    }



    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView requesthasbeensent,recemail,acceptreject,noreqsent,machinename,eraseuser,erasemach;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);


            requesthasbeensent=itemView.findViewById(R.id.requesthasbeensent);
            recemail=itemView.findViewById(R.id.namev);
            machinename=itemView.findViewById(R.id.mname);
            acceptreject=itemView.findViewById(R.id.acceptreject);
            noreqsent=itemView.findViewById(R.id.norequestsent);
            eraseuser=itemView.findViewById(R.id.eraseruser);
            erasemach=itemView.findViewById(R.id.erasermach);

        }
    }


}

