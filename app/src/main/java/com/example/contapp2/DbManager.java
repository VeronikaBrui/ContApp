package com.example.contapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbManager  extends SQLiteOpenHelper {
    public static final String DBNAME = "ContApp5";
    public static final String TABLECONT = "Contacts";
    public static final String TABLEUSER = "Users";
    public static final int VER = 1;

    public DbManager(@Nullable Context context){
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "Create table " + TABLECONT+ "(phone text primary key, name text, email text)";
        db.execSQL(qry);
        String qry2 = "Create table " + TABLEUSER+ "(email text primary key, name text, phone text, password text, confpassword text)";
        db.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String qry = "drop table if exists "+TABLECONT+"";
        String qry2 = "drop table if exists "+TABLEUSER+"";
        db.execSQL(qry);
        db.execSQL(qry2);

    }
    public Boolean insertData (String p1, String p2, String p3, String p4){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("email", p1);
        cv.put("name", p2);
        cv.put("phone", p3);
        cv.put("password", p4);

        long res = db.insert("Users", null, cv);
        System.out.println(res);

        if (res == -1)
            return false;
        else
            return true;
    }

    public boolean checkEmailExist (String email){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM Users where email = ?", new String[]{email});

        if (cursor.getCount()> 0) {
            return true;
        }else
            return false;
    }
    public boolean checkPassword (String email, String password ) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT email, password FROM Users WHERE email = ? and password = ?" , new String[]{email, password});

        if (cursor.getCount() > 0){
            return true;
        }else
            return false;
    }
}
