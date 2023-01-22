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

public class viewrequestadapter extends RecyclerView.Adapter<viewrequestadapter.myviewholder>
{
    viewrequestmodel data[];

    Context context;




    public viewrequestadapter(viewrequestmodel[] data, Context context) {

        this.data = data;
        this.context=context;


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.requestitems,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final viewrequestmodel temp=data[position];
        holder.recemail.setText(data[position].getMyemail());
          holder.machinename.setText(data[position].getMachinename());
         holder.t2.setText(data[position].getFrom());
         holder.t2.setText(data[position].getVar());
         holder.t4.setText(data[position].getStatus());
        // holder.t5.setText(data[position].getEmail());
        //Glide.with(holder.t1.getContext()).load("http://192.168.1.10/API/upload/"+data[position].getImage()).into(holder.img);
        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,requestdetails.class);
                intent.putExtra("username",temp.getCurrentusername());
                intent.putExtra("location",temp.getSenderlocation());
                intent.putExtra("date",temp.getDate());
                intent.putExtra("number",temp.getNumber());
                intent.putExtra("desc",temp.getDescription());
                intent.putExtra("hours",temp.getHours());




                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.length;
    }



    class myviewholder extends RecyclerView.ViewHolder
    {
        Button viewdetails;
        TextView recemail,t2,t3,t4,machinename;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            //  img=itemView.findViewById(R.id.img);
            recemail=itemView.findViewById(R.id.from);
             t2=itemView.findViewById(R.id.t2);
            // t3=itemView.findViewById(R.id.t3);
              t4=itemView.findViewById(R.id.t4);
             machinename=itemView.findViewById(R.id.machinenameholder);
             viewdetails=itemView.findViewById(R.id.viewdetailbtn);
        }
    }


}
