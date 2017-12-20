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
        TextView scoreLabel = (TextView) findViewById(R.id.resultView);
        TextView highScoreUser = (TextView) findViewById(R.id.highscoreUserView);
        TextView highScoreLabel = (TextView) findViewById(R.id.highscoreView);

        SharedPreferences prefs = this.getSharedPreferences("GAME_MODE", Context.MODE_PRIVATE);

        scoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
        String scoreString = getIntent().getStringExtra("SCORE_String");
        scoreLabel.setText(scoreString);
        int score = scoreToInt(scoreString);

        highScoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
        highScoreUser.setText("Noch nicht bestätigt");
        String highScoreString = prefs.getString("HIGHSCORE_String", "9:99:999");
        highScoreLabel.setText(highScoreString);
        int highScore = scoreToInt(highScoreString);

        if (score < highScore)
        {
            highScore = score;
            highScoreUser.setText(SharedPrefManager.getInstance(this).getUsername());
            highScoreLabel.setText(Integer.toString(highScore));
            prefs.edit().putInt("highScore", highScore).apply();
        }
        else
        {
            highScoreLabel.setText(Integer.toString(highScore));
        }
    }

    private int scoreToInt(String string)
    {
        String[] scoreArray = new String[3];
        scoreArray = string.split(":");
        String s = "" + scoreArray[0] + scoreArray[1] + scoreArray[2];
        int score = Integer.parseInt(s);
        return score;
    }

    public void back (View view)
    {
        finish();
        startActivity(new Intent(Result.this, SinglePlayerActivity.class));
    }

}
