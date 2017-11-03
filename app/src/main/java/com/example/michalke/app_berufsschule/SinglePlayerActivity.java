package com.example.michalke.app_berufsschule;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SinglePlayerActivity extends AppCompatActivity
{
    ImageButton buttonRight, buttonLeft, buttonUpperRight, buttonUpperLeft, buttonExit;
    ImageView stickmanMove, goal;

    //float currentX = stickmanMove.getX();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        buttonRight = (ImageButton) findViewById(R.id.buttonRight);
        buttonRight.setBackground(null);
        buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        buttonLeft.setBackground(null);
        buttonUpperRight = (ImageButton) findViewById(R.id.buttonUpperRight);
        buttonUpperRight.setBackground(null);
        buttonUpperLeft = (ImageButton) findViewById(R.id.buttonUpperLeft);
        buttonUpperLeft.setBackground(null);
        stickmanMove = (ImageView) findViewById(R.id.stickmanMove);
        goal = (ImageView) findViewById(R.id.goal);
        buttonExit = (ImageButton) findViewById(R.id.exit);
        buttonExit.setBackground(null);
    }


    public void right(View view)
    {
        Toast.makeText(SinglePlayerActivity.this, "Button RIGHT geklickt", Toast.LENGTH_SHORT).show();
        //currentX++;
        //stickmanMove.setX(currentX);
        stickmanMove.setX(+ 10.0f);
    }

    //public void left(View view)
    //{
    //    stickmanMove.setY(50);
    //}

    //public void upperRight(View view)
    //{
    //    stickmanMove.setX(currentX += 10);
    //    stickmanMove.setY(currentY += 10);
    //}

    //public void upperLeft(View view)
    //{
    //    stickmanMove.setX(currentX -= 10);
    //    stickmanMove.setY(currentY += 10);
    //}

    //Sobald x = 535 überschritten soll das Spiel beendet sein!

    public void exit (View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Sie wolen das Spiel verlassen?");
        alertDialogBuilder.setPositiveButton("Verlassen!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
                startActivity(new Intent(SinglePlayerActivity.this, UserActivity.class));
            }
        });
        alertDialogBuilder.setNegativeButton("Weiter spielen!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(SinglePlayerActivity.this, "Sie bleiben im Spiel und es wird weiter ausgeführt!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
