package com.example.michalke.app_berufsschule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class SinglePlayerActivity extends AppCompatActivity
{
    ImageButton buttonRight, buttonLeft, buttonUpperRight, buttonUpperLeft;
    ImageView stickmanMove;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        //stickmanMove.setX(50);
        //stickmanMove.setY(50);
    }


    //public void right()
    //{
      //stickmanMove.setX(50);
    //}

    //public void left()
    //{
      //stickmanMove.setY(50);
    //}

    //  public void upperRight()
    //{
    //   stickmanMove.setX(currentX += 10);
    //  stickmanMove.setY(currentY += 10);
    //}

    //public void upperLeft()
    //{
    //   stickmanMove.setX(currentX -= 10);
    //   stickmanMove.setY(currentY += 10);
    //}

    //Sobald x = 535 überschritten soll das Spiel beendet sein!
}
