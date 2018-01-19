package com.example.michalke.app_berufsschule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity
{
    private EditText eRSurname, eRForename, eRUsername, eRPassword;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, UserActivity.class));
            return;
        }

        eRSurname = (EditText) findViewById(R.id.eRSurname);
        eRForename = (EditText) findViewById(R.id.eRForename);
        eRUsername = (EditText) findViewById(R.id.eRUsername);
        eRPassword = (EditText) findViewById(R.id.eRPassword);

        progressDialog = new ProgressDialog(this);
    }

    private void registerUser()
    {
        final String surname = eRSurname.getText().toString().trim();
        final String forename = eRForename.getText().toString().trim();
        final String username = eRUsername.getText().toString().trim();
        final String password = eRPassword.getText().toString().trim();

        if (TextUtils.isEmpty(surname))
        {
            eRSurname.setError("Nachnamen eingeben!");
        }
        else if(TextUtils.isEmpty(forename))
        {
            eRForename.setError("Vornamen eingeben!");
        }
        else if (TextUtils.isEmpty(username))
        {
            eRUsername.setError("Nutzernamen eingeben!");
        }
        else if (TextUtils.isEmpty(password))
        {
            eRPassword.setError("Passwort eingeben!");
        }
        else
        {
            progressDialog.setMessage("Nutzer registrieren...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("surname", surname);
                    params.put("forename", forename);
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
    public void rRegister(View view)
    {
        registerUser();
    }

    public void rLogin(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
