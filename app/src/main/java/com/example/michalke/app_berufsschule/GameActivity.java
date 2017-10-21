package com.example.michalke.app_berufsschule;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity
{
    ImageButton buttonRight, buttonLeft, buttonUpperRight, buttonUpperLeft;
    ImageView stickmanMove;

    //int currentX = (int)stickmanMove.getX();
    //int currentY = (int)stickmanMove.getY();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonRight = (ImageButton) findViewById(R.id.buttonRight);
        buttonRight.setBackground(null);
        buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        buttonLeft.setBackground(null);
        buttonUpperRight = (ImageButton) findViewById(R.id.buttonUpperRight);
        buttonUpperRight.setBackground(null);
        buttonUpperLeft = (ImageButton) findViewById(R.id.buttonUpperLeft);
        buttonUpperLeft.setBackground(null);
        stickmanMove = (ImageView) findViewById(R.id.stickmanMove);
    }

    public void right()
    {
        //stickmanMove.setX(currentX += 10);

    }

    public void left()
    {
        //stickmanMove.setY(currentX -= 10);
    }

    public void upperRight()
    {
        //stickmanMove.setX(currentX += 10);
        //stickmanMove.setY(currentY += 10);
    }

    public void upperLeft()
    {
        //stickmanMove.setX(currentX -= 10);
        //stickmanMove.setY(currentY += 10);
    }

    //Sobald x = 535 überschritten soll das Spiel beendet sein!
}
