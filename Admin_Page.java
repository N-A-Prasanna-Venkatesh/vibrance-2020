package com.example.vibrance20201;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class Admin_Page extends AppCompatActivity {
EditText name,password;
Button btn;
String u_name,u_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__page);
        btn=findViewById(R.id.btn);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        u_name=name.getText().toString().trim();
        u_password=password.getText().toString().trim();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_details();

            }
        });
    }
    private void upload_details( ){
        //final ProgressDialog loading = ProgressDialog.show(this,"Creating Access","Please wait");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbwQUVw_Pr4yFfHv1bOJa9sRtXA4i3ASk8pXRXw2D89ehUrhaA0F/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
    // loading.dismiss();
    Toast.makeText(Admin_Page.this, "Access Granted to :" + u_name, Toast.LENGTH_LONG).show();
    //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
    //startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                      //  loading.dismiss();
                        Toast.makeText(Admin_Page.this, "Access not Granted to :"+u_name, Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parma = new HashMap<>();

                //here we pass params
                parma.put("action","grantAccess");
                parma.put("name",u_name);
                parma.put("password",u_password);
                return parma;
            }
        };
        int socketTimeOut = 10000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
}
