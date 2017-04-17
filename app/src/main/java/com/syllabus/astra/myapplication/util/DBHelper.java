package com.syllabus.astra.myapplication.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/15.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATEBASE_NAME = "demo.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATEBASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS teacher"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR, mon VARCHAR, tues VARCHAR, wed VARCHAR, thur VARCHAR, fri VARCHAR, sat VARCHAR, sun VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS teacherlist"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR, name VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS course"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR, mon VARCHAR, tues VARCHAR, wed VARCHAR, thur VARCHAR, fri VARCHAR, sat VARCHAR, sun VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS courselist"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR, name VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS classroomlist"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR, district VARCHAR, building VARCHAR, classroom VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}


