package com.example.michalke.app_berufsschule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity
{
    private TextView tUUsername;
    //ImageButton bScore, bSettings;
    int item_selection = 0;

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

        //bScore = (ImageButton) findViewById(R.id.bScore);
        //bScore.setBackground(null);

        //bSettings = (ImageButton) findViewById(R.id.bSettings);
        //bSettings.setBackground(null);
    }

    public void confirm (View view)
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

    public void play(View view)
    {
        registerForContextMenu(view);
        openContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.levels, menu);
        MenuItem menuOne = menu.findItem(R.id.level1);
        MenuItem menuDual = menu.findItem(R.id.level2);
        MenuItem menuDev = menu.findItem(R.id.level3);
        if(item_selection == 1)
        {
            menuOne.setChecked(true);
        }
        else if (item_selection == 2)
        {
            menuDual.setChecked(true);
        }
        else if (item_selection == 3)
        {
            menuDev.setChecked(true);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.level1:
                Toast.makeText(UserActivity.this, item.getTitle() + "\n--> Sie spielen jetzt LEVEL 1!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SinglePlayer_Level1.class));
                item_selection = 1;
                break;
            case R.id.level2:
                Toast.makeText(UserActivity.this, item.getTitle() + "\n--> Sie spielen jetzt LEVEL 2!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SinglePlayer_Level2.class));
                item_selection = 2;
                break;
            case R.id.level3:
                Toast.makeText(UserActivity.this, item.getTitle() + "\n--> Sie spielen jetzt LEVEL 3!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SinglePlayer_Level3.class));
                item_selection = 3;
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void logout (View view)
    {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
        SharedPrefManager.getInstance(this).logout();
    }

    /*public void viewScore (View view)
    {
        startActivity(new Intent(UserActivity.this, ResultActivity.class));
    }

    public void settings (View view)
    {
        PopupMenu menu = new PopupMenu(UserActivity.this, bSettings);
        menu.getMenuInflater().inflate(R.menu.menu, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                Toast.makeText(UserActivity.this, " " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        menu.show();
    }*/
}
