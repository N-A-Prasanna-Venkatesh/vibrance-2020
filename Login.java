package com.example.vibrance20201;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
//  FindUserPassword ------action parameter;   Password , Username ---paramater
public class Login extends AppCompatActivity {
    EditText name,version,password;
    Button login;
    TextView attempts;
    int fl=0;
    int ct=5;
    int count=5;
    String url_c;
    ProgressDialog loading;
    public Boolean v=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name =  findViewById(R.id.name);
        password =  findViewById(R.id.password);
        login =  findViewById(R.id.login);
        version=findViewById(R.id.version);
        if(version.equals("1")){
            url_c="https://script.google.com/macros/s/AKfycbygCpVnMdevNErXl2kz5r1-7DuEDkLxMO-5gMGMdD7AwHGiAvGR/exec";
        }
        if(version.equals("2")){

            url_c="https://script.google.com/macros/s/AKfycbxIX2fWu-TPk0k7gbyU0qcePcCzpZAWEBF2EPEGsJaZ0ecoEjg/exec";
        }

        final ArrayList<String> Users=new ArrayList<>(Arrays.asList("prasanna","hashwanth","ajithesh","premalatha"));
        final ArrayList<String> UserPassword=new ArrayList<>(Arrays.asList("prasanna","hashwanth","ajithesh","premalatha"));
     /*  Users.add("a");
        Users.add("b");
        Users.add("c");
        Users.add("d");
        UserPassword.add("1");
        UserPassword.add("2");
        UserPassword.add("3");
        UserPassword.add("4");*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names=name.getText().toString().trim();
                String passwords=password.getText().toString().trim();

                for (int i = 0; i <UserPassword.size() ; i++) {
                    if(names.equals(Users.get(i))&&passwords.equals(UserPassword.get(i))){
                        Intent intent =new Intent(Login.this,MainActivity.class);
                        intent.putExtra("User",names);
                        startActivity(intent);
                    }

                }


                 if(names.equals("admin")&&passwords.equals("prasanna")){
                    Intent intent=new Intent(Login.this,Admin_Page.class);
                    name.setText("");
                    password.setText("");
                    startActivity(intent);
                }else{
                    check_if_present(names,passwords);
                }

                }
                /*if (Users.indexOf(names)==UserPassword.indexOf(passwords)){
                    if(UserPassword.indexOf(passwords)>0){
                        Intent i=new Intent(Login.this,MainActivity.class);
                        i.putExtra("User", names);
                        startActivity(i);
                    }
                }else{
                    if (ct==1){
                        ct=0;
                        attempts.setText("No. of attempts left =0");
                        login.setEnabled(false);
                    }else{
                        ct--;
                        attempts.setText("No. of attempts left ="+ct);
                    }

                }*/
            }
            );
    }

public void check_if_present(final String Username, final String Userpassword){
   // loading =  ProgressDialog.show(this,"Verifying","please wait",false,true);

    StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbwQUVw_Pr4yFfHv1bOJa9sRtXA4i3ASk8pXRXw2D89ehUrhaA0F/exec",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if(response.equals("Not Found")){
                      //  loading.dismiss();
                        Toast.makeText(Login.this, "Username Not Found!", Toast.LENGTH_SHORT).show();
                    }else{
                        if(response.equals(Userpassword)){
                            //loading.dismiss();
                            name.setText("");
                            password.setText("");
                            Intent intent=new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                           // loading.dismiss();
                            Toast.makeText(Login.this, "BLAH BLAF BLAH", Toast.LENGTH_SHORT).show();
                        }
                            /*else{if(response.equals("p")){
                            Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                            name.setText("");
                            password.setText("");
                            loading.dismiss();
                        }
                        }*/
                        }

                    }
                }
            ,

            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
    ){
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> parmas = new HashMap<>();

            //here we pass params
            parmas.put("action","FindUserPassword");
            parmas.put("Username",Username);
            /*parmas.put("event",event_names);
            parmas.put("register_number",reg_nos);
            parmas.put("email",emails);
            parmas.put("phone",phones);
            parmas.put("amount",amounts);
            parmas.put("volunteer",volunteer);*/

            return parmas;
        }
    };

    int socketTimeOut = 1000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    stringRequest.setRetryPolicy(policy);

    RequestQueue queue = Volley.newRequestQueue(this);
    queue.add(stringRequest);


}


}

