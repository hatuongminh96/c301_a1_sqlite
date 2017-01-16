package com.example.minhnguyen.tuongmin_sizebook;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by MinhNguyen on 14/1/17.
 */

public class DbOperator extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MY_DB.db";

    public static final String CREATE_FIRST_TABLE = "CREATE TABLE IF NOT EXISTS "
            + PersonContract.PersonEntry.TABLE_NAME + "( id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PersonContract.PersonEntry.COLUMN_NAME_NAME + " TEXT NOT NULL, "
            + PersonContract.PersonEntry.COLUMN_NAME_DATE + " TEXT, "
            + PersonContract.PersonEntry.COLUMN_NAME_NECK + " NUMERIC, "
            + PersonContract.PersonEntry.COLUMN_NAME_BUST + " NUMERIC, "
            + PersonContract.PersonEntry.COLUMN_NAME_CHEST + " NUMERIC, "
            + PersonContract.PersonEntry.COLUMN_NAME_WAIST + " NUMERIC, "
            + PersonContract.PersonEntry.COLUMN_NAME_HIP + " NUMERIC, "
            + PersonContract.PersonEntry.COLUMN_NAME_INSEAM + " NUMERIC, "
            + PersonContract.PersonEntry.COLUMN_NAME_COMMENT + " TEXT);";

    public static final String DELETE_FIRST_TABLE = "DROP TABLE IF EXISTS " + PersonContract.PersonEntry.TABLE_NAME + ";";

    public DbOperator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DELETE_FIRST_TABLE);
        db.execSQL(CREATE_FIRST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        onCreate(db);
    }
}
