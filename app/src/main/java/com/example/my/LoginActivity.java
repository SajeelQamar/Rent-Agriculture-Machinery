package com.example.my;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    // private static final String url = "http://192.168.1.5/API/loginn.php";
   private static final String url = "http://192.168.43.10/API/loginn.php";
    EditText editemail,editpass;
    Button btnLogin;
    TextView tv,createaccount;
    SharedPreferences sp;
    SessionManagement sessionManagement;
    int userid;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkuserexistence();
       // tv=(TextView)findViewById(R.id.status);
        createaccount=(TextView)findViewById(R.id.createaccount);
        editemail=(EditText)findViewById(R.id.email);
        editpass=(EditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.loginbtn);
        sessionManagement=new SessionManagement(getApplicationContext());
        id=sessionManagement.getsession();
        sp=getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=editemail.getText().toString().trim();
                final String pass=editpass.getText().toString().trim();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if (!object.getBoolean("sucsses")){
                                final String name=object.getString("name");
                                final String location=object.getString("location");
                                final String phonenumber=object.getString("phonenumber");


                                SharedPreferences.Editor editor=sp.edit();
                                editor.putString("email",editemail.getText().toString());
                                editor.putString("password",editpass.getText().toString());
                                editor.putString("location",location.toString());
                                editor.putString("phonenumber",phonenumber.toString());
                                editor.putString("currentusername",name.toString());
                                editor.commit();
                                editor.apply();



                                 Intent intent=new Intent(getApplicationContext(),searchvehicle.class);
                                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                 intent.putExtra("id",id);
                                 intent.putExtra("name",name);
                                 startActivity(intent);
                                 finish();

                            }else {
                                editemail.setText("");
                                editpass.setText("");
                                Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT).show();
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
                        parms.put("emailapi",email);
                        parms.put("passwordapi",pass);
                        return parms;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

    }


    public void checkuserexistence()
    {
     SharedPreferences sp=getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        if (sp.contains("email")){

            startActivity(new Intent(getApplicationContext(),searchvehicle.class));}
        else{
            Toast.makeText(getApplicationContext(),"Please Login",Toast.LENGTH_SHORT).show();}

    }
}