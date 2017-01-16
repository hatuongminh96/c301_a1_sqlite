package com.example.minhnguyen.tuongmin_sizebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MinhNguyen on 14/1/17.
 */

public class DbManipulate extends DbOperator {
    public DbManipulate(Context context){
        super(context);
    }

    private static final String Name = "Name";
    private static final String Date = "Date";
    private static final String Neck = "Neck";
    private static final String Bust = "Bust";
    private static final String Chest = "Chest";
    private static final String Waist = "Waist";
    private static final String Hip = "Hip";
    private static final String Inseam = "Inseam";
    private static final String Comment = "Comment";

    SQLiteDatabase db = this.getWritableDatabase();

    public void addNew(ContentValues person){
        db.insert(PersonContract.PersonEntry.TABLE_NAME, null, person);
    }
}
