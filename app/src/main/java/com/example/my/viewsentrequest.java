package com.example.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class viewsentrequest extends AppCompatActivity {
    SharedPreferences sp;
    RecyclerView recview;
    public static String url="http://192.168.43.10/API/viewsentrequestfetch.php";
    String currentusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsentrequest);
        SharedPreferences sp=getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        currentusername=sp.getString("email","abc");
        recview=(RecyclerView)findViewById(R.id.viewsentrequestrecview);
        process(currentusername);

    }

    public  void process(String id){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();


                viewrequestsentmodel data[] = gson.fromJson(response, viewrequestsentmodel[].class);

                viewrequestsentadapter adapter = new viewrequestsentadapter(data, getApplicationContext());
                recview.setAdapter(adapter);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(viewsentrequest.this, 1);
                recview.setLayoutManager(gridLayoutManager);



                try {

                    JSONObject object=new JSONObject(response);
                    if (!object.getBoolean("sucsses")){





                    }else {

                       // Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String>parms=new HashMap<String, String>();
                parms.put("currentuserid",id);

                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}