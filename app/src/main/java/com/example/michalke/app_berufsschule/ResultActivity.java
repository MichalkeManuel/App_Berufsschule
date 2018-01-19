package com.example.michalke.app_berufsschule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity
{

    int item_selection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView level = (TextView) findViewById(R.id.tLevelView);
        TextView scoreUser = (TextView) findViewById(R.id.scoreUserView);
        TextView scoreLabel = (TextView) findViewById(R.id.resultView);
        TextView highScoreUser = (TextView) findViewById(R.id.highscoreUserView);
        TextView highScoreLabel = (TextView) findViewById(R.id.highscoreView);

        SharedPreferences prefs = this.getSharedPreferences("GAME_MODE", Context.MODE_PRIVATE);

        int levelInt = getIntent().getIntExtra("LEVEL", 0);


        String levelString = getIntent().getStringExtra("LEVEL_No");
        level.setText(levelString);

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
        //Toast.makeText(ResultActivity.this,);
    }

    public void home (View view)
    {
        startActivity(new Intent(ResultActivity.this, UserActivity.class));
    }

    public void level(View view)
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
                Toast.makeText(ResultActivity.this, item.getTitle() + "\n--> Sie spielen jetzt LEVEL 1!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SinglePlayer_Level1.class));
                item_selection = 1;
                break;
            case R.id.level2:
                Toast.makeText(ResultActivity.this, item.getTitle() + "\n--> Sie spielen jetzt LEVEL 2!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SinglePlayer_Level2.class));
                item_selection = 2;
                break;
            case R.id.level3:
                Toast.makeText(ResultActivity.this, item.getTitle() + "\n--> Sie spielen jetzt LEVEL 3!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SinglePlayer_Level3.class));
                item_selection = 3;
                break;
        }
        return super.onContextItemSelected(item);
    }
}
