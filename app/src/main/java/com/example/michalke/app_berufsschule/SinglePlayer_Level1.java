package com.example.michalke.app_berufsschule;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePlayer_Level1 extends AppCompatActivity
{
    Button bStartGame, bRestartGame;
    ImageButton buttonRight, buttonLeft, buttonUpRight, buttonUpLeft, buttonDownRight, buttonDownLeft, buttonExit;
    ImageView stickmanMove, goal;
    TextView tTimerWatch;
    Handler customhandler = new Handler();
    long startTime, timeInMilliseconds, timeSwapBuff, updateTime = 0L;

    Runnable updateTimerThread = new Runnable()
    {
        @Override
        public void run()
        {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updateTime/1000);
            int mins = secs/60;
            secs %= 60;
            int miliseconds = (int) (updateTime%1000);
            tTimerWatch.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", miliseconds));
            customhandler.postDelayed(this, 0);
        }
    };

    float currentX;
    float currentY = 450.0f;
    int level = 1;
    String levelNo = "Level 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_level1);

        bStartGame = (Button) findViewById(R.id.bStartGame);
        bRestartGame = (Button) findViewById(R.id.bRestartGame);
        bRestartGame.setEnabled(false);
        tTimerWatch = (TextView) findViewById(R.id.tTimerWatch);
        buttonRight = (ImageButton) findViewById(R.id.buttonRight);
        buttonRight.setBackground(null);
        buttonRight.setEnabled(false);
        buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        buttonLeft.setBackground(null);
        buttonLeft.setEnabled(false);
        buttonUpRight = (ImageButton) findViewById(R.id.buttonUpRight);
        buttonUpRight.setBackground(null);
        buttonUpRight.setEnabled(false);
        buttonUpLeft = (ImageButton) findViewById(R.id.buttonUpLeft);
        buttonUpLeft.setBackground(null);
        buttonUpLeft.setEnabled(false);
        buttonDownRight = (ImageButton) findViewById(R.id.buttonDownRight);
        buttonDownRight.setBackground(null);
        buttonDownRight.setEnabled(false);
        buttonDownLeft = (ImageButton) findViewById(R.id.buttonDownLeft);
        buttonDownLeft.setBackground(null);
        buttonDownLeft.setEnabled(false);
        stickmanMove = (ImageView) findViewById(R.id.stickmanMove);
        goal = (ImageView) findViewById(R.id.goal);
        buttonExit = (ImageButton) findViewById(R.id.exit);
        buttonExit.setBackground(null);
    }

    public void startGame(View view)
    {
        buttonRight.setEnabled(true);
        buttonLeft.setEnabled(true);
        buttonUpRight.setEnabled(true);
        buttonUpLeft.setEnabled(true);
        buttonDownRight.setEnabled(true);
        buttonDownLeft.setEnabled(true);
        startTime = SystemClock.uptimeMillis();
        customhandler.postDelayed(updateTimerThread, 0);
        bStartGame.setEnabled(false);
        bRestartGame.setEnabled(true);
    }

    public void restartGame(View view)
    {
        startActivity(new Intent(getApplicationContext(), SinglePlayer_Level1.class));
        finish();
    }

    public boolean isInGoal()
    {
        if(currentX >= (goal.getX() - goal.getWidth()/2 + 17.5) && currentX <= (goal.getX() + goal.getWidth()/2 - 17.5))
        {
            if(currentY >= (goal.getY() - goal.getHeight()/2 + 17.5) && currentY <= (goal.getY() + goal.getHeight()/2 - 17.5))
            {
                return true;
            }
        }
        return false;
    }

    public void right(View view)
    {
        stickmanMove.setX(currentX += 50.0f);
        if(isInGoal())
        {
            inGoal();
        }
    }

    public void left(View view)
    {
        stickmanMove.setX(currentX -= 50.0f);
        if(isInGoal())
        {
            inGoal();
        }
    }

    public void upRight(View view)
    {
        stickmanMove.setY(currentY -= 50.0f);
        stickmanMove.setX(currentX += 50.0f);
        if(isInGoal())
        {
            inGoal();
        }

    }

    public void upLeft(View view)
    {
        stickmanMove.setX(currentX -= 50.0f);
        stickmanMove.setY(currentY -= 50.0f);
        if(isInGoal())
        {
            inGoal();
        }
    }

    public void downRight(View view)
    {
        stickmanMove.setX(currentX += 50.0f);
        stickmanMove.setY(currentY += 50.0f);
        if(isInGoal())
        {
            inGoal();
        }
    }

    public void downLeft(View view)
    {
        stickmanMove.setX(currentX -= 50.0f);
        stickmanMove.setY(currentY += 50.0f);
        if(isInGoal())
        {
            inGoal();
        }
    }

    public void exit (View view)
    {
        timeSwapBuff += timeInMilliseconds;
        customhandler.removeCallbacks(updateTimerThread);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Sie wollen das Spiel verlassen?");
        alertDialogBuilder.setPositiveButton("Verlassen!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
                startActivity(new Intent(SinglePlayer_Level1.this, UserActivity.class));
            }
        });
        alertDialogBuilder.setNegativeButton("Weiter spielen!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                startTime = SystemClock.uptimeMillis();
                customhandler.postDelayed(updateTimerThread, 0);
                Toast.makeText(SinglePlayer_Level1.this, "Sie bleiben im Spiel und es wird weiter ausgeführt!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

    public void inGoal()
    {
        customhandler.removeCallbacks(updateTimerThread);
        buttonRight.setEnabled(false);
        buttonLeft.setEnabled(false);
        buttonUpRight.setEnabled(false);
        buttonUpLeft.setEnabled(false);
        buttonDownRight.setEnabled(false);
        buttonDownLeft.setEnabled(false);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Sie haben das Level mit "+ tTimerWatch.getText() + " Sekunden geschafft");
        alertDialogBuilder.setPositiveButton("Nächstes Level?", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Toast.makeText(SinglePlayer_Level1.this, "Das nächste Level ist noch in Arbeit!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SinglePlayer_Level1.this, SinglePlayer_Level2.class));
            }
        });
        alertDialogBuilder.setNegativeButton("Speichern?", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent resultIntent = new Intent(SinglePlayer_Level1.this, ResultActivity.class);
                resultIntent.putExtra("SCORE_String", tTimerWatch.getText());
                //resultIntent.putExtra("SCORE_Value", );
                resultIntent.putExtra("LEVEL", level);
                resultIntent.putExtra("LEVEL_No", levelNo);
                startActivity(resultIntent);

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }
}
