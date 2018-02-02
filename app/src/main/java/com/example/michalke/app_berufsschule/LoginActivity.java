package com.example.michalke.app_berufsschule;

import android.app.ProgressDialog;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity
{
    private EditText eUsername, ePassword;
    private TextView tLHinweis;
    private Button bLogin;
    private ProgressDialog progressDialog;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, UserActivity.class));
            return;
        }*/

        eUsername = (EditText) findViewById(R.id.eLUsername);
        ePassword = (EditText) findViewById(R.id.eLPassword);
        tLHinweis = (TextView) findViewById(R.id.tHinweis);
        bLogin = (Button) findViewById(R.id.bLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Bitte warten...");
    }

    private void userLogin()
    {
        final String username = eUsername.getText().toString().trim();
        final String password = ePassword.getText().toString().trim();

        if (TextUtils.isEmpty(username))
        {
            eUsername.setError("Nutzernamen eingeben!");
        }
        else if (TextUtils.isEmpty(password))
        {
            ePassword.setError("Passwort eingeben!");
        }
        else
        {
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    progressDialog.dismiss();
                    try
                    {
                        JSONObject obj = new JSONObject(response);
                        if(!obj.getBoolean("error"))
                        {
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(obj.getInt("id"), obj.getString("surname"), obj.getString("forename"), obj.getString("username"));
                            finish();
                            startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            counter--;
                            tLHinweis.setText("Sie haben noch " + Integer.toString(counter) + " Versuche!");
                            if (counter == 0)
                            {
                                bLogin.setEnabled(false);
                                Toast.makeText(LoginActivity.this, "3 Mal falsch eingegeben... App wurde beendet!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }

    public void login(View view)
    {
        userLogin();
    }

    public void lRegister(View view)
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}

