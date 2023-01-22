package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

public class requestdetails extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    MaterialButton accept,reject;
    String sendername;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestdetails);

        tv1=(TextView) findViewById(R.id.nam);
        tv2=(TextView) findViewById(R.id.locat);
        tv3=(TextView) findViewById(R.id.dat);
        tv4=(TextView) findViewById(R.id.numb);
        tv5=(TextView) findViewById(R.id.descc);
        tv6=(TextView) findViewById(R.id.hour);
        accept=(MaterialButton) findViewById(R.id.acceptreqbtn);
        reject=(MaterialButton) findViewById(R.id.rejectreqbtn);

        sp = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        final String currentusername=sp.getString("email","abc");
        sendername=getIntent().getStringExtra("username");
       tv2.setText(getIntent().getStringExtra("location"));
        tv3.setText(getIntent().getStringExtra("date"));
        tv4.setText(getIntent().getStringExtra("number"));
        tv5.setText(getIntent().getStringExtra("desc"));
        tv6.setText(getIntent().getStringExtra("hours"));
        tv1.setText(sendername);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final String RG ="http://192.168.43.10/API/requestacceptbutton.php";
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, RG, new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),"Accepted Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),searchvehicle.class));

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams()  {
                            Map<String,String> parms=new HashMap<String, String>();
                            final String var="Request Accepted!";
                            parms.put("currentuserid",currentusername);
                            parms.put("input",var);
                            return parms;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);


            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String RG ="http://192.168.43.10/API/requestacceptbutton.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, RG, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Rejected Successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),searchvehicle.class));

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String,String> parms=new HashMap<String, String>();
                        final String var="Request Rejected!";
                        parms.put("currentuserid",currentusername);
                        parms.put("input",var);
                        return parms;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }
        });


    }
}