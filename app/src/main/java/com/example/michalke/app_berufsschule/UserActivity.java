package com.example.michalke.app_berufsschule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity
{
    private TextView tUUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        tUUsername = (TextView) findViewById(R.id.messageView);
        tUUsername.setText(SharedPrefManager.getInstance(this).getUsername());
    }

    public void open (View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Bitte bestätigen!\nIst das Ihr Username?");
        alertDialogBuilder.setPositiveButton("JA!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(UserActivity.this, "Account bestätigt!", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNegativeButton("NEIN!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
                SharedPrefManager.getInstance(UserActivity.this).logout();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void logout (View view)
    {
        finish();
        startActivity(new Intent(UserActivity.this, LoginActivity.class));
        SharedPrefManager.getInstance(this).logout();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Toast.makeText(this, "Noch gibt es keine Einstellungen!", Toast.LENGTH_SHORT).show();
        return true;
    }*/
}
