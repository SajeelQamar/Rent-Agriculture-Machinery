package com.example.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class searchvehicle extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private static final String url = "http://192.168.43.10/API/json_user_fetch.php";
    // private static final String url = "http://192.168.43.10/API/json_user_fetch.php";
    RecyclerView recview;
    retrieveimageAdapter adapter;
    SharedPreferences sp;
    SessionManagement sessionManagement;
    public  ArrayList<retrieveimagemodel> fileArrayList = new ArrayList<>();
    ArrayList<retrieveimagemodel> arr = new ArrayList<retrieveimagemodel>();
    SwipeRefreshLayout refreshLayout;
    TextView tv;
    EditText sarch;
    int userid;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchvehicle);
        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        sarch =(EditText) findViewById(R.id.edittextsearch);
        sarch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                fileArrayList.clear();
                if(s.toString().isEmpty()){
                    recview.setAdapter(new retrieveimageAdapter(arr,getApplicationContext()));
                    if(adapter!=null){             adapter.notifyDataSetChanged();}
                }
                else {

                    Filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {






            }
        });



        processdata();


        sessionManagement = new SessionManagement(getApplicationContext());
        sp = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);

    //    tv = (TextView) findViewById(R.id.userheadername);


        //int id=getIntent().getIntExtra("id",0);
        // tv.setText(String.valueOf(id));
  //      String username=sp.getString("currentusername","abc");
//        tv.setText(username);

        Toolbar toolbar = findViewById(R.id.TOOLBAR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_bar);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addposT:
                startActivity(new Intent(getApplicationContext(), Addpost.class));
                break;
            case R.id.logout:

                sp.edit().remove("email").commit();
                sp.edit().remove("password").commit();
                sp.edit().apply();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
            case R.id.viewrequesT:
                startActivity(new Intent(getApplicationContext(), Recieverequest.class));
                break;
            case R.id.Myrequestmenu:

                startActivity(new Intent(getApplicationContext(), viewsentrequest.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public void processdata() {

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                retrieveimagemodel data[] = gson.fromJson(response, retrieveimagemodel[].class);

                for (int i = 0; i < data.length; i++) {
                    arr.add(data[i]);
                }


                retrieveimageAdapter adapter = new retrieveimageAdapter(arr, getApplicationContext());
                recview.setAdapter(adapter);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(searchvehicle.this, 2);
                recview.setLayoutManager(gridLayoutManager);






            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }


    public void Filter(String text){
        for(retrieveimagemodel model:arr){
            if (model.getName().toLowerCase().contains(text)){
                fileArrayList.add(model);
            }

        }
        recview.setAdapter(new retrieveimageAdapter(fileArrayList,getApplicationContext()));
if(adapter!=null){        adapter.notifyDataSetChanged();}
    }



}





