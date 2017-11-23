package com.example.michalke.app_berufsschule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class Result extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreUser = (TextView) findViewById(R.id.scoreUserView);
        TextView scoreLabel = (TextView)findViewById(R.id.resultView);
        TextView highScoreUser = (TextView) findViewById(R.id.highscoreUserView);
        TextView highScoreLabel = (TextView) findViewById(R.id.highscoreView);

        SharedPreferences prefs = this.getSharedPreferences("GAME_MODE", Context.MODE_PRIVATE);

        scoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
        String scoreString = getIntent().getStringExtra("SCORE_String");
        scoreLabel.setText(scoreString);
        String[] scoreArray = new String[3];
        scoreArray = scoreString.split(":");
        String s = "" + scoreArray[0] + scoreArray[1] +scoreArray[2];
        int score = Integer.parseInt(s);

        //highScoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
        highScoreUser.setText("Noch nicht bestätigt");
        String highScoreString = prefs.getString("HIGHSCORE_String", "-:--:---");
        highScoreLabel.setText(highScoreString);

//        int score = getIntent().getIntExtra("SCORE_Value", 0);
        int highScore = 0; //prefs.getInt("HIGH_SCORE", 0);


        if (highScore == 0)
        {
            highScore = score;
            highScoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
            highScoreLabel.setText(scoreString);
        }
        else if (score < highScore)
        {
            scoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
            highScoreLabel.setText(score);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }
        else
        {
            highScoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
            highScoreLabel.setText(highScore);
        }

    }

    public void back (View view)
    {
        finish();
        startActivity(new Intent(Result.this, SinglePlayerActivity.class));
    }

}
