package com.example.minhnguyen.tuongmin_sizebook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.aList);
        final Button button = (Button) findViewById(R.id.addNewButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddEdit.class);
                startActivity(intent);
            }
        });
        DbOperator DbHelper = new DbOperator(getApplicationContext());
        SQLiteDatabase db = DbHelper.getReadableDatabase();
        String[] projection = {PersonContract.PersonEntry.COLUMN_NAME_NAME, PersonContract.PersonEntry.COLUMN_NAME_ID};
        Cursor cursor = db.query(PersonContract.PersonEntry.TABLE_NAME, projection, null, null, null, null, null);

        List peopleInDb = new ArrayList<>();
        final List IDinDb = new ArrayList<>();
        while (cursor.moveToNext()) {
            peopleInDb.add(cursor.getString(0));
            IDinDb.add(cursor.getString(1));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, peopleInDb);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentEdit = new Intent(view.getContext(), AddEdit.class);
                //System.out.println(position);
                System.out.println(IDinDb.get(position).toString());

                intentEdit.putExtra("id", IDinDb.get(position).toString());

                startActivity(intentEdit);



                //setContentView(R.layout.activity_main);


            }
        });
    }

}
