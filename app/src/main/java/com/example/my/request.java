package com.example.my;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class request extends AppCompatActivity {

    String[] items = new String[]{"1 hour", "2 hours", "3 hours"};
    Spinner dropdown;
    private DatePickerDialog datePickerDialog;
    private Button dateButton, click;
    TextView tv;
    int PLACE_PICKER_REQUESR = 1;
    EditText number, description,editlocation;
    String reciver_email, location, machineryname;
    String myemail;
    static String var = "From:";
    static String currentusername;
    SharedPreferences sp;
    //private static final String RG = "http://192.168.1.5/API/request.php";
     private static final String RG ="http://192.168.43.10/API/request.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);




        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        click = findViewById(R.id.click);
        number = (EditText) findViewById(R.id.Enternumber);
        description = (EditText) findViewById(R.id.enterdecbox);
        editlocation = (EditText) findViewById(R.id.enterrequestlocation);
        dateButton.setText(getTodaysDate());
        sp = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
        myemail = sp.getString("email", "abc");
        location = sp.getString("location", "abc");
        currentusername = sp.getString("currentusername", "abc");

        // tv.setText(dropdown.getSelectedItem().toString());
        //tv = (TextView)dropdown.getSelectedView();
        //String result = tv.getText().toString();

        dropdown = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        reciver_email = getIntent().getStringExtra("email");
        machineryname = getIntent().getStringExtra("machinename");



        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String NUMBER = number.getText().toString().trim();
                final String DESCRIPTION = description.getText().toString().trim();
                final String DATE = dateButton.getText().toString().trim();
                final String Enterlocation = editlocation.getText().toString().trim();
                final String HOURS = dropdown.getSelectedItem().toString();

                sentrequest(myemail, reciver_email, currentusername, NUMBER, DATE, HOURS, machineryname, DESCRIPTION, Enterlocation, var);
                viewsentrequest(myemail, reciver_email, machineryname);

            }
        });

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }


    private void sentrequest(final String Myemail, final String RecEmail, final String Currentusername, final String Number, final String Date, final String Hour, final String Machinename, final String Desc, final String loc, final String VAR) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RG, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Request Submitted", Toast.LENGTH_LONG).show();
                number.setText("null");
                description.setText("");
                editlocation.setText("");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("myemail", Myemail);
                parms.put("recemail", RecEmail);
                parms.put("location", loc);
                parms.put("desc", Desc);
                parms.put("num", Number);
                parms.put("date", Date);
                parms.put("hour", Hour);
                parms.put("machinename", Machinename);
                parms.put("currentusername", Currentusername);
                parms.put("var", VAR);

                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void viewsentrequest(final String Myemail, final String RecEmail, final String Machinename) {
        final String RG = "http://192.168.43.10/API/viewsentrequest.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RG, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                number.setText("null");
                description.setText("");
                editlocation.setText("");

                startActivity(new Intent(getApplicationContext(),searchvehicle.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                final String messege = "Request has Sent!";
                final String accept = "";
                final String erase1 = "User:";
                final String erase2 = "Machinery:";
                parms.put("myemail", Myemail);
                parms.put("recemail", RecEmail);
                parms.put("machinename", Machinename);
                parms.put("messege", messege);
                parms.put("accept", accept);
                parms.put("erase1", erase1);
                parms.put("erase2", erase2);
                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }



    }




