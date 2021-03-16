package com.example.locksafe.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.locksafe.model.User;
// Name: Sinead

//Student No.:R00153463

//Open Source Project: Locksafe

//*

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_USER = "userdetails";

    private static final String KEY_ID = "user_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
//Creates User table to hold their details
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PASSWORD + " TEXT" + ")";

    private String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        onCreate(db);

    }

    public void addUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(KEY_NAME, u.getName());
        vals.put(KEY_EMAIL, u.getEmail());
        vals.put(KEY_PASSWORD, u.getPassword());

        db.insert(TABLE_USER,null,vals);
        db.close();
    }

    public boolean userCheck(String email) {
        String[] cols = {
                KEY_ID
        };

        SQLiteDatabase db = this.getWritableDatabase();
        String select = KEY_EMAIL + " =?";
        String[] selectargs = {email};
        Cursor c = db.query(TABLE_USER, cols, select, selectargs, null, null, null);
        int count = c.getCount();
        c.close();
        db.close();
        if (count > 0) {
            return true;
        }
        return false;
    }

    public boolean userCheck(String email, String password) {
        String[] cols = {
                KEY_ID
        };

        SQLiteDatabase db = this.getWritableDatabase();
        String select = KEY_EMAIL + " =?" + " AND " + KEY_PASSWORD + " =?";
        String[] selectargs = { email, password };
        Cursor c = db.query(TABLE_USER, cols, select, selectargs, null, null, null);
        int count = c.getCount();
        c.close();
        db.close();
        if (count > 0) {
            return true;
        }
        return false;
    }


}




