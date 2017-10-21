package com.example.michalke.app_berufsschule;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    Button bLogin;
    EditText eUsername, ePassword;
    TextView tAnleitung, tLHinweis, tRegisterLink;
    int counter = 3;
    public static final String EXTRA_MESSAGE = "com.expample.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eUsername = (EditText) findViewById(R.id.eLUsername);
        ePassword = (EditText) findViewById(R.id.eLPassword);

        tAnleitung = (TextView) findViewById(R.id.tLAnleitung);
        tLHinweis = (TextView) findViewById(R.id.tLHinweis);
    }

    public void login (View view)
    {
        String username = "Manuel";
        String password = "12345";

        Intent intent = new Intent(LoginActivity.this, UserActivity.class);

        if(eUsername.getText().toString().equals(username) && ePassword.getText().toString().equals(password))
        {
            Toast.makeText(LoginActivity.this, "Glückwunsch! Sie sind jetzt eingeloggt", Toast.LENGTH_SHORT).show();
            String message = eUsername.getText().toString().trim();
            intent.putExtra(EXTRA_MESSAGE, message);
            LoginActivity.this.startActivity(intent);
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Ihre Eingaben sind nicht korrekt!", Toast.LENGTH_SHORT).show();
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

    public void register (View view)
    {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(registerIntent);
    }
}