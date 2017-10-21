package com.example.michalke.app_berufsschule;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
{
    DatabaseManager dbmgr;
    SQLiteDatabase sqlDaBa;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        //dbmgr = new DatabaseManager(this);
        Toast.makeText(this, "Datensätze einfügen", Toast.LENGTH_SHORT).show();
        //long rowID = dbmgr.insertRecord();
        Toast.makeText(this, "Aufruf Ausgabemethode", Toast.LENGTH_SHORT).show();
        //String tablecontent = dbmgr.output();
        //Toast.makeText(this, tablecontent, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause()
    {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
        dbmgr.close();
        Toast.makeText(this, "Datenbank geschlossen", Toast.LENGTH_SHORT).show();
    }

    /*@Override
    protected void onResume()
    {
        super.onResume();
        sqlDaBa = dbmgr.getReadableDatabase();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        Cursor tableCursor = sqlDaBa.rawQuery(DatabaseManager.CLASS_SELECT_RAW, null);
        Toast.makeText(this, "Datenbank geöffnet", Toast.LENGTH_SHORT).show();
    }*/
}