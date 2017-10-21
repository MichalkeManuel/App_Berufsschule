package com.example.michalke.app_berufsschule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity
{
    ImageButton bSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        TextView messageView = (TextView) findViewById(R.id.messageView);
        messageView.setText(message);

        bSettings = (ImageButton) findViewById(R.id.bSettings);
        bSettings.setBackground(null);
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
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void game (View view)
    {
        Intent intent = new Intent(UserActivity.this, GameActivity.class);
        UserActivity.this.startActivity(intent);
    }

    public  void settings (View view)
    {

    }

    public void close (View view)
    {
        finish();
    }
}
