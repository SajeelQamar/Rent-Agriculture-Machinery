package com.example.my;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.window.SplashScreen;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {

    EditText name,email,phonenumber,password,abc,location;
    MaterialButton createnow;
    TextView status;

    MaterialButton login;
    //private static final String url="http://192.168.1.6/API/Setdata.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.Email);
        phonenumber=(EditText) findViewById(R.id.phonenumber);
        password=(EditText) findViewById(R.id.Password);

        location=(EditText)findViewById(R.id.Location);
        status=(TextView) findViewById(R.id.status);
        createnow=(MaterialButton) findViewById(R.id.createnow);



    createnow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            processdata(name.getText().toString(),email.getText().toString(),phonenumber.getText().toString(),password.getText().toString(),location.getText().toString());
        }
    });
    }
    public void processdata(String namE,String emaiL,String phonenumbeR,String passworD,String locatioN)
    {
      Call<responsemodel> call=apiController.getInstance()
              .getapi()
              .getregister(namE,emaiL,phonenumbeR,passworD,locatioN);

      call.enqueue(new Callback<responsemodel>() {
          @Override
          public void onResponse(Call<responsemodel> call, Response<responsemodel> response) {
              responsemodel obj=response.body();
              status.setText(obj.getMessege());
              name.setText("");
              email.setText("");
              phonenumber.setText("");
              password.setText("");
              location.setText("");
              startActivity(new Intent(getApplicationContext(),LoginActivity.class));

          }

          @Override
          public void onFailure(Call<responsemodel> call, Throwable t) {
              status.setText("Went wrong");
              name.setText("");
              email.setText("");
              phonenumber.setText("");
              password.setText("");
              location.setText("");


          }
      });
    }
}



