package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Bookmark extends AppCompatActivity {


    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        ListView listView = (ListView) findViewById(R.id.listMovies);
        myDB = new DBAdapter(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getData();
        String empty = "Bookmarknya Kosong";
        if (data.getCount() == 0) {
            Toast.makeText(this,empty, Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }

}