package com.example.locksafe.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.locksafe.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_USER = "userdetails";

    private static final String KEY_ID = "user_id";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PASSWORD = "user_password";

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
    
    public List<User> fetchUsers(){
        String[] cols = {
                KEY_ID,
                KEY_NAME,
                KEY_EMAIL,
                KEY_PASSWORD
        };
        String order = KEY_NAME + " ASC";
        List<User> uList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_USER, cols, null,null,null,null, order);

        if (c.moveToFirst()){
            do{
                User u = new User();
                u.setId(Integer.parseInt(c.getString(c.getColumnIndex(KEY_ID))));
                u.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                u.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                u.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));

                uList.add(u);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return uList;
    }

    public void userUpdate(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(KEY_NAME, u.getName());
        vals.put(KEY_EMAIL, u.getEmail());
        vals.put(KEY_PASSWORD, u.getPassword());

        db.update(TABLE_USER, vals, KEY_ID + " = ?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    public void delUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?", new String[]{String.valueOf(u.getId())});
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
