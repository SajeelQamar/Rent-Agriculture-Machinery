package com.example.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;

public class selectmachinery extends AppCompatActivity {

    TextView name,model,specification,earlyhourlyrate,location;
    ImageView imageView;
    String photo,image_email,machinename;
    MaterialButton request,buttoncall;
    SharedPreferences sp;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmachinery);
        request=(MaterialButton)findViewById(R.id.request);
        buttoncall=(MaterialButton)findViewById(R.id.callbtn);
        name=(TextView) findViewById(R.id.showname);
        model=(TextView) findViewById(R.id.showmodel);
        specification=(TextView) findViewById(R.id.showspecification);
        earlyhourlyrate=(TextView) findViewById(R.id.showrate);
        location=(TextView) findViewById(R.id.showlocation);
        imageView=(ImageView) findViewById(R.id.img);

        String usernum=getIntent().getStringExtra("usernum");
        photo=getIntent().getStringExtra("imagename");
       // Glide.with(selectmachinery.this).load("http://192.168.1.5/API/upload/"+photo).into(imageView);
       Glide.with(selectmachinery.this).load("http://192.168.43.10/API/upload/"+photo).into(imageView);
        name.setText(getIntent().getStringExtra("showname"));
        model.setText(getIntent().getStringExtra("showmodel"));
        earlyhourlyrate.setText(getIntent().getStringExtra("showrate"));
        specification.setText(getIntent().getStringExtra("showspecs"));
        location.setText(getIntent().getStringExtra("showlocation"));
        image_email=getIntent().getStringExtra("email");
        machinename=getIntent().getStringExtra("showname");

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),request.class);
                intent.putExtra("email",image_email);
                intent.putExtra("machinename",machinename);
                sp = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
                String currentuser=sp.getString("email","");
                if(currentuser.equals(image_email)){ Toast.makeText(getApplicationContext(), "Went Wrong,Requesting Your own Post", Toast.LENGTH_LONG).show();
                     }else{


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);}


            }
        });
        buttoncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
                String currentuser=sp.getString("email","");
                if(currentuser.equals(image_email)){ Toast.makeText(getApplicationContext(), "Went wrong,Calling Your own Number", Toast.LENGTH_LONG).show();
                }else{
                dialContactPhone(usernum);}
            }
        });



    }
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }



}