package com.example.chatting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EmailUser.db";
    private static final String CREATE_TBL_USERS = "CREATE TABLE "
            + DatabaseContract.Customers.TABLE_NAME + " ("
            + DatabaseContract.Customers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Customers.COL_NAME + " TEXT NOT NULL, "
            + DatabaseContract.Customers.COL_CONTACT + " TEXT,"
            + DatabaseContract.Customers.COL_LOCATION + " TEXT,"
            + DatabaseContract.Customers.COL_EMAIL + " TEXT,"
            + DatabaseContract.Customers.COL_PASSWORD+ " TEXT )";
    private static final String CREATE_TBL_USERS1 = "CREATE TABLE "
            + DatabaseContract.MilkMan.TABLE_NAME + " ("
            + DatabaseContract.MilkMan._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.MilkMan.COL_NAME + " TEXT NOT NULL, "
            + DatabaseContract.MilkMan.COL_CONTACT + " TEXT,"
            + DatabaseContract.MilkMan.COL_LOCATION + " TEXT,"
            + DatabaseContract.MilkMan.COL_EMAIL + " TEXT,"
            + DatabaseContract.MilkMan.COL_PASSWORD+ " TEXT,"
            + DatabaseContract.MilkMan.COL_QUALITY+ " TEXT,"
            + DatabaseContract.MilkMan.COL_QUANTITY+ " INTEGER,"
            + DatabaseContract.MilkMan.COL_PRICE+ " INTEGER)";

    private static final String CREATE_TBL_USERS3 = "CREATE TABLE "
            + DatabaseContract.OrderT.TABLE_NAME + " ("
            +DatabaseContract.OrderT._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.OrderT.COL_PLACED_BY + " INTEGER NOT NULL, "
            + DatabaseContract.OrderT.COL_PLACED_TO + " INTEGER,"
            + DatabaseContract.OrderT.COL_QUANTITY+ " INTEGER,"
            +DatabaseContract.OrderT.COL_QUALITY+ " TEXT,"
            + DatabaseContract.OrderT.COL_PRICE+ " INTEGER)";

    private static final String CREATE_TBL_USERS4 = "CREATE TABLE "
            + DatabaseContract.Review.TABLE_NAME + " ("
            +DatabaseContract.Review._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Review.COL_PLACED_BY + " INTEGER NOT NULL, "
            + DatabaseContract.Review.COL_PLACED_TO + " INTEGER,"
            + DatabaseContract.Review.COL_REVIEW+ " TEXT)";
    private static final String CREATE_TBL_USERS5 = "CREATE TABLE "
            + DatabaseContract.Riders.TABLE_NAME + " ("
            + DatabaseContract.Riders._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Riders.COL_NAME + " TEXT NOT NULL, "
            + DatabaseContract.Riders.COL_CONTACT + " TEXT,"
            + DatabaseContract.Riders.COL_LOCATION + " TEXT,"
            + DatabaseContract.Riders.COL_EMAIL + " TEXT,"
            + DatabaseContract.Riders.COL_PASSWORD+ " TEXT )";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_USERS);
        db.execSQL(CREATE_TBL_USERS1);
        db.execSQL(CREATE_TBL_USERS3);
        db.execSQL(CREATE_TBL_USERS4);
        db.execSQL(CREATE_TBL_USERS5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
