package com.example.michalke.app_berufsschule;

import android.app.ProgressDialog;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity
{
    private ProgressDialog progressDialog;
    int item_selection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView level = (TextView) findViewById(R.id.tLevelView);
        TextView scoreUser = (TextView) findViewById(R.id.scoreUserView);
        TextView scoreLabel = (TextView) findViewById(R.id.resultView);
        //TextView scoreNrLabel = (TextView) findViewById(R.id.scoreView);
        //TextView highScoreUser = (TextView) findViewById(R.id.highscoreUserView);
        TextView highScoreLabel = (TextView) findViewById(R.id.highscoreView);

        final int levelInt = getIntent().getIntExtra("LEVEL", 0);
        final String level_no = Integer.toString(levelInt);
        final String user = SharedPrefManager.getInstance(this).getUsername();

        String levelString = getIntent().getStringExtra("LEVEL_No");
        level.setText(levelString);

        scoreUser.setText(user);
        final String scoreString = getIntent().getStringExtra("SCORE_String");
        scoreLabel.setText(scoreString);
        final int scoreInt = scoreToInt(scoreString);
        final String score = Integer.toString(scoreInt);
        highScoreLabel.setText(score);
        Toast.makeText(ResultActivity.this, "Level: " + level_no + " " + user + " Score: " + score, Toast.LENGTH_SHORT).show();


        //#################
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Bitte warten...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_PUSHSCORE, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            SharedPrefManager.getInstance(getApplicationContext()).pushScore(obj.getInt("id"), obj.getInt("level_no"), obj.getString("user"), obj.getInt("score"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("level_no", level_no);
                    params.put("user", user);
                    params.put("score", score);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //################

        /*if (levelInt == 1)
        {
            highScoreUser.setText("HighScore-Liste: 'aktuelle IP-Adresse/app_reglog/ScoresLV1.php");
        }
        else if (levelInt == 2)
        {
            highScoreUser.setText("HighScore-Liste: 'aktuelle IP-Adresse/app_reglog/ScoresLV2.php");
        }
        else if (levelInt == 3)
        {
            highScoreUser.setText("HighScore-Liste: 'aktuelle IP-Adresse/app_reglog/ScoresLV3.php");
        }*/

        //################
        /*StringRequest stReq = new StringRequest(Request.Method.POST, Constants.URL_GETSCORE, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error"))
                    {
                        SharedPrefManager.getInstance(getApplicationContext()).highscoreToSharedPrefMan(obj.getString("user"), obj.getInt("score"));
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("level_no", level_no);
                params.put("user", user);
                params.put("score", score);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stReq);
        //final String  hsUser = SharedPrefManager.getInstance(this).getHighscoreUser();
        //highScoreUser.setText(hsUser);
        //final String highscore = SharedPrefManager.getInstance(this).getHighscoreValue();
        //highScoreLabel.setText(highscore);*/
        //################

    }

    private int scoreToInt(String string)
    {
        String[] scoreArray = new String[3];
        scoreArray = string.split(":");
        String s = "" + scoreArray[0] + scoreArray[1] + scoreArray[2];
        int score = Integer.parseInt(s);
        return score;
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
