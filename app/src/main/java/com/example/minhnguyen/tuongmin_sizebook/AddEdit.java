package com.example.minhnguyen.tuongmin_sizebook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class AddEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__edit);
        final Button button = (Button) findViewById(R.id.button_save);

        System.out.println("Here yet?");

        Intent rI = getIntent();
        String id = rI.getStringExtra("id");
        System.out.println("Your id is: "+ id);

        if (id != null) {
            DbOperator DbHelper = new DbOperator(getApplicationContext());
            SQLiteDatabase db = DbHelper.getReadableDatabase();
            String[] projection = {PersonContract.PersonEntry.COLUMN_NAME_NAME,
                    PersonContract.PersonEntry.COLUMN_NAME_DATE,
                    PersonContract.PersonEntry.COLUMN_NAME_NECK,
                    PersonContract.PersonEntry.COLUMN_NAME_BUST,
                    PersonContract.PersonEntry.COLUMN_NAME_CHEST,
                    PersonContract.PersonEntry.COLUMN_NAME_WAIST,
                    PersonContract.PersonEntry.COLUMN_NAME_HIP,
                    PersonContract.PersonEntry.COLUMN_NAME_INSEAM,
                    PersonContract.PersonEntry.COLUMN_NAME_COMMENT};
            final String selection = PersonContract.PersonEntry.COLUMN_NAME_ID + " = ?";
            //String IDinQ = id;
            final String[] selectionArgs = { id };
            System.out.println(id);
            Cursor cursor = db.query(PersonContract.PersonEntry.TABLE_NAME, projection,selection,selectionArgs,null,null,null);
            cursor.moveToFirst();

            for (int i=0; i < 9; i++) {
                System.out.print(cursor.getString(i)+" ");
            }
            System.out.println();

            setInfo(cursor);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ContentValues someOne = getInfo();
                    if (someOne != null) {
                        DbOperator DbHelper = new DbOperator(getApplicationContext());
                        SQLiteDatabase db = DbHelper.getWritableDatabase();
                        int count = db.update(PersonContract.PersonEntry.TABLE_NAME, someOne, selection, selectionArgs);
                        System.out.println("Updated");
                        Toast.makeText(getApplicationContext(), someOne.getAsString(PersonContract.PersonEntry.COLUMN_NAME_NAME)+" updated!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });

        }
        else {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ContentValues someOne = getInfo();
                    if (someOne != null) {
                        DbOperator DbHelper = new DbOperator(getApplicationContext());
                        SQLiteDatabase db = DbHelper.getWritableDatabase();
                        db.insert(PersonContract.PersonEntry.TABLE_NAME, null, someOne);
                        System.out.println("Inserted");
                        Toast.makeText(getApplicationContext(), someOne.getAsString(PersonContract.PersonEntry.COLUMN_NAME_NAME)+" added!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }

    public ContentValues getInfo() {
        ContentValues person = new ContentValues();

        EditText eName = (EditText) findViewById(R.id.name_editText);
        EditText eDate = (EditText) findViewById(R.id.date_editText);
        EditText eNeck = (EditText) findViewById(R.id.neck_editText);
        EditText eBust = (EditText) findViewById(R.id.bust_editText);
        EditText eChest = (EditText) findViewById(R.id.chest_editText);
        EditText eWaist = (EditText) findViewById(R.id.waist_editText);
        EditText eHip = (EditText) findViewById(R.id.hip_editText);
        EditText eInseam = (EditText) findViewById(R.id.inseam_editText);
        EditText eComment = (EditText) findViewById(R.id.comment_editText);

        String name = eName.getText().toString();
        String date = eDate.getText().toString();
        String neck = eNeck.getText().toString();
        String bust = eBust.getText().toString();
        String chest = eChest.getText().toString();
        String waist = eWaist.getText().toString();
        String hip = eHip.getText().toString();
        String inseam = eInseam.getText().toString();
        String comment = eComment.getText().toString();

        if (!name.isEmpty()) {
            person.put(PersonContract.PersonEntry.COLUMN_NAME_NAME, name);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_DATE, date);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_NECK, neck);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_BUST, bust);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_CHEST, chest);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_WAIST, waist);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_HIP, hip);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_INSEAM, inseam);
            person.put(PersonContract.PersonEntry.COLUMN_NAME_COMMENT, comment);
            System.out.println(name);
            return person;
        }

        else {
            Toast.makeText(getApplicationContext(), "A man needs a name!", Toast.LENGTH_LONG).show();
            System.out.println("here");
            return null;
        }
    }

    public void setInfo(Cursor cursor){

        EditText eName = (EditText) findViewById(R.id.name_editText);
        EditText eDate = (EditText) findViewById(R.id.date_editText);
        EditText eNeck = (EditText) findViewById(R.id.neck_editText);
        EditText eBust = (EditText) findViewById(R.id.bust_editText);
        EditText eChest = (EditText) findViewById(R.id.chest_editText);
        EditText eWaist = (EditText) findViewById(R.id.waist_editText);
        EditText eHip = (EditText) findViewById(R.id.hip_editText);
        EditText eInseam = (EditText) findViewById(R.id.inseam_editText);
        EditText eComment = (EditText) findViewById(R.id.comment_editText);

        eName.setText(cursor.getString(0));
        eDate.setText(cursor.getString(1));
        eNeck.setText(cursor.getString(2));
        eBust.setText(cursor.getString(3));
        eChest.setText(cursor.getString(4));
        eWaist.setText(cursor.getString(5));
        eHip.setText(cursor.getString(6));
        eInseam.setText(cursor.getString(7));
        eComment.setText(cursor.getString(8));
    }

}

