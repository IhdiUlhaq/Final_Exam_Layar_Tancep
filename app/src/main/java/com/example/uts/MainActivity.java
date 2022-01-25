package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, ListLayarActivity.class);
        startActivity(intent);
    }
    public void ShowListTancep (View view){
        Intent intent = new Intent(this, ListLayarActivity.class);
        startActivity(intent);
    }

    public void ShowAboutUs (View view){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }
    public void TampilkenLayartancep(View view){
        Intent intent = new Intent(this, Bookmark.class);
        startActivity(intent);
    }


}