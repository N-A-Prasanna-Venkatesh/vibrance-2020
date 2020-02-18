package com.example.vibrance20201;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   public EditText p_name,event_name,reg_no,email,phone,amount,version;
    Button register;
    String url_c;

String TransactionGenerator="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);//NIGHT MODE FEATURE--CHANGE YES TO NO FOR DAY MODE.
        p_name=findViewById(R.id.p_name);
        event_name=findViewById(R.id.event_name);
        reg_no=findViewById(R.id.reg_no);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        amount=findViewById(R.id.amount);
        register=findViewById(R.id.register);
       version=findViewById(R.id.version);
        if(version.equals("1")){
            url_c="https://script.google.com/macros/s/AKfycbygCpVnMdevNErXl2kz5r1-7DuEDkLxMO-5gMGMdD7AwHGiAvGR/exec";
        }
        if(version.equals("2")){

            url_c="https://script.google.com/macros/s/AKfycbxIX2fWu-TPk0k7gbyU0qcePcCzpZAWEBF2EPEGsJaZ0ecoEjg/exec";
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sb = "";
                for (int i = 0; i < 9; i++) {

                    int index = (int)(TransactionGenerator.length()* Math.random());

                    sb+=TransactionGenerator.charAt(index);

                }




                upload_details(sb);

            }
        });
    }


    private void upload_details(final String sb){
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
        final String p_names = p_name.getText().toString().trim();
        final String event_names = event_name.getText().toString().trim();
        final String reg_nos =reg_no.getText().toString().trim();
        final String emails = email.getText().toString().trim();
        final String phones = phone.getText().toString().trim();
        final String amounts = amount.getText().toString().trim();




        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbygCpVnMdevNErXl2kz5r1-7DuEDkLxMO-5gMGMdD7AwHGiAvGR/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            loading.dismiss();
                            Toast.makeText(MainActivity.this, "YOU HAVE SUCCESSFULLY REGISTERED", Toast.LENGTH_LONG).show();
                            p_name.setText("");
                            event_name.setText("");
                            reg_no.setText("");
                            email.setText("");
                            phone.setText("");
                            amount.setText("");
                            //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            //startActivity(intent);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("name",p_names);
                parmas.put("event",event_names);
                parmas.put("register_number",reg_nos);
                parmas.put("email",emails);
                parmas.put("phone",phones);
                parmas.put("amount",amounts);
                parmas.put("transactionID",sb);

                return parmas;
            }
        };
        int socketTimeOut = 10000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
}
