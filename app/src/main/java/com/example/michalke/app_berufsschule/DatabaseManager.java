package com.example.michalke.app_berufsschule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

/**
 * Created by michalke on 13.10.2017.
 * Last change by michalke on 19.10.2017.
 */

public class DatabaseManager extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDatabase.db";
    private static final String DATABASE_TABLE = "userData";
    public static final String CLASS_SELECT_RAW = "SELECT * FROM " + DatabaseManager.DATABASE_TABLE;
    private SQLiteDatabase sqlDaBa;
    private Context activity;

    public DatabaseManager(Context activity)
    {
        super(activity, DATABASE_NAME, null, DATABASE_VERSION);
        Toast.makeText(activity, "DatabaseManager", Toast.LENGTH_SHORT).show();
        sqlDaBa = getWritableDatabase();
        this.activity = activity;
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDaBa)
    {
        Toast.makeText(activity, "onCreate", Toast.LENGTH_SHORT).show();
        String sql = "CREATE TABLE" + DATABASE_TABLE +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "surname VARCHAR(20) NOT NULL," +
                "forename VARCHAR(20) NOT NULL," +
                "username VARCHAR(20) NOT NULL," +
                "username VARCHAR(20) NOT NULL)";
        sqlDaBa.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDaBa, int oldVersion, int newVersion)
    {
        Toast.makeText(activity, "onUpgrade", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close()
    {
        Toast.makeText(activity, "close", Toast.LENGTH_SHORT).show();
        sqlDaBa.close();
    }

    public String output()
    {
        Toast.makeText(activity, "output", Toast.LENGTH_SHORT).show();
        Cursor result = sqlDaBa.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
        String mContent = "";
        if (result == null)
        {
            Toast.makeText(activity, "Tabelle leer", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (result.moveToNext())
            {
                mContent += result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + "\n";
            }
        }
        sqlDaBa.close();
        return mContent;
    }

    public long insertRecord()
    {
        Toast.makeText(activity, "insertRecord", Toast.LENGTH_SHORT).show();
        ContentValues cv = new ContentValues();
        cv.put("surname", "Michalke");
        cv.put("forename", "Manuel");
        cv.put("username", "Michalke");
        cv.put("password", "12345");
        long rowId = sqlDaBa.insert(DATABASE_TABLE, null, cv);
        return rowId;
    }
}
