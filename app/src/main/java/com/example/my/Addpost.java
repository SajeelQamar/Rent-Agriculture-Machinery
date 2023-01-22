package com.example.my;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Addpost extends AppCompatActivity {
    ImageView image;
    Button btnchoose, submit;
    int PICK_IMAGE_REQUEST = 111;
   // String URL ="http://192.168.1.5/API/fileupload.php";
    String URL ="http://192.168.43.10/API/fileupload.php";
    Bitmap bitmap;
    ProgressDialog progressDialog;
    EditText name,model,specs,ehrate,location;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        image = (ImageView)findViewById(R.id.img);
        submit = (Button)findViewById(R.id.submit);

        //opening image chooser option
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Addpost.this);
                progressDialog.setMessage("Uploading, please wait...");
                progressDialog.show();

                //converting image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                sp=getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);

                //sending image to server
                name=(EditText)findViewById(R.id.namebox);
                model=(EditText)findViewById(R.id.modelbox);
                specs=(EditText)findViewById(R.id.specbox);
                ehrate=(EditText)findViewById(R.id.earlybox);
                location=(EditText)findViewById(R.id.locationbox);
                final String email=sp.getString("email","abc");

                final String Name=name.getText().toString().trim();
                final String Model=model.getText().toString().trim();
                final String Specs=specs.getText().toString().trim();
                final String Ehrate=ehrate.getText().toString().trim();
                final String Location=location.getText().toString().trim();
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        name.setText("");
                        model.setText("");
                        specs.setText("");
                        ehrate.setText("");
                        location.setText("");

                        image.setImageResource(R.drawable.ic_launcher_foreground);
                        Toast.makeText(getApplicationContext(),s.toString(), Toast.LENGTH_LONG).show();
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Addpost.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();
                    }
                }) {
                    //adding parameters to send
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        sp=getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
                        final String usernum=sp.getString("phonenumber","abcc");
                        parameters.put("name",Name);
                        parameters.put("model",Model);
                        parameters.put("specs",Specs);
                        parameters.put("ehrate",Ehrate);
                        parameters.put("location",Location);
                        parameters.put("email",email);
                        parameters.put("phonenumber",usernum);
                        parameters.put("upload", imageString);
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(Addpost.this);
                rQueue.add(request);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}