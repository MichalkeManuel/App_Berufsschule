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

public class SinglePlayerActivity extends AppCompatActivity
{
    Button bStartGame;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        bStartGame = (Button) findViewById(R.id.bStartGame);
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
    }


    public void right(View view)
    {
        if (currentX <= goal.getX())
        {
            stickmanMove.setX(currentX += 50.0f);
        }
        else
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
                    Toast.makeText(SinglePlayerActivity.this, "Das nächste Level ist noch in Arbeit!", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialogBuilder.setNegativeButton("Speichern?", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(SinglePlayerActivity.this, "Speichern ist momentan noch nicht möglich!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
        }
    }

    public void left(View view)
    {
        if (currentX <= goal.getX())
        {
            stickmanMove.setX(currentX -= 50.0f);
        }
        else
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
                    Toast.makeText(SinglePlayerActivity.this, "Das nächste Level ist noch in Arbeit!", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialogBuilder.setNegativeButton("Speichern?", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(SinglePlayerActivity.this, "Speichern ist momentan noch nicht möglich!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
        }
    }

    public void upRight(View view)
    {
        if (currentX <= goal.getX())
        {
            stickmanMove.setY(currentY -= 50.0f);
            stickmanMove.setX(currentX += 50.0f);
        }
        else
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
                    Toast.makeText(SinglePlayerActivity.this, "Das nächste Level ist noch in Arbeit!", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialogBuilder.setNegativeButton("Speichern?", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(SinglePlayerActivity.this, "Speichern ist momentan noch nicht möglich!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
        }
    }

    public void upLeft(View view)
    {
        if (currentX <= goal.getX())
        {
            stickmanMove.setY(currentY -= 50.0f);
            stickmanMove.setX(currentX -= 50.0f);
        }
        else
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
                    Toast.makeText(SinglePlayerActivity.this, "Das nächste Level ist noch in Arbeit!", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialogBuilder.setNegativeButton("Speichern?", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(SinglePlayerActivity.this, "Speichern ist momentan noch nicht möglich!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
        }
    }

    public void downRight(View view)
    {
        if (currentX <= goal.getX())
        {
            stickmanMove.setY(currentY += 50.0f);
            stickmanMove.setX(currentX += 50.0f);
        }
        else
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
                    Toast.makeText(SinglePlayerActivity.this, "Das nächste Level ist noch in Arbeit!", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialogBuilder.setNegativeButton("Speichern?", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(SinglePlayerActivity.this, "Speichern ist momentan noch nicht möglich!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
        }
    }

    public void downLeft(View view)
    {
        if (currentX <= goal.getX())
        {
            stickmanMove.setY(currentY += 50.0f);
            stickmanMove.setX(currentX -= 50.0f);
        }
        else
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
                    Toast.makeText(SinglePlayerActivity.this, "Das nächste Level ist noch in Arbeit!", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialogBuilder.setNegativeButton("Speichern?", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(SinglePlayerActivity.this, "Speichern ist momentan noch nicht möglich!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
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
                startActivity(new Intent(SinglePlayerActivity.this, UserActivity.class));
            }
        });
        alertDialogBuilder.setNegativeButton("Weiter spielen!", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                startTime = SystemClock.uptimeMillis();
                customhandler.postDelayed(updateTimerThread, 0);
                Toast.makeText(SinglePlayerActivity.this, "Sie bleiben im Spiel und es wird weiter ausgeführt!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }
}
