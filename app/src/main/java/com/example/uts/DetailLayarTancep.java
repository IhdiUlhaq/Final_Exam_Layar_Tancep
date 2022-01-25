package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import com.example.uts.model.LayarTancep;
import com.squareup.picasso.Picasso;


public class DetailLayarTancep extends AppCompatActivity implements View.OnClickListener {


    Button BtnTambahken;
    Button BtnHapusken;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layar_tancep);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        TextView txtTitle = findViewById(R.id.txtId);
        TextView txtOverview = findViewById(R.id.txtName);
        TextView txtDate = findViewById(R.id.txtName2);
        TextView txtOriginalTitle = findViewById(R.id.txtName3);
        ImageView imgView = findViewById(R.id.icon);

        txtTitle.setText(intent.getStringExtra("txtTitle"));
        txtOverview.setText(intent.getStringExtra("txtOverview"));
        txtDate.setText(intent.getStringExtra("txtDate"));
        txtOriginalTitle.setText(intent.getStringExtra("txtOriginalTitle"));

        String urlpicture = intent.getStringExtra("picture");
        Picasso.get().load(urlpicture).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imgView);

        BtnTambahken = findViewById(R.id.BtnTambahken);
        BtnHapusken = findViewById(R.id.BtnHapusken);
        BtnTambahken.setOnClickListener(this);
        BtnHapusken.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BtnTambahken:
                Intent intent = getIntent();
                String movie_title = intent.getStringExtra("txtTitle");
                DBAdapter myAdapter = new DBAdapter(DetailLayarTancep.this);
                myAdapter.insertData(movie_title);
                String jadi = getString(R.string.LTJadi);
                Toast.makeText(getApplicationContext(), jadi, Toast.LENGTH_LONG).show();

                break;

            case R.id.BtnHapusken:
                intent = getIntent();
                movie_title = intent.getStringExtra("txtTitle");
                myAdapter = new DBAdapter(DetailLayarTancep.this);
                myAdapter.removeData(movie_title);
                String gajadi = getString(R.string.LTGajadi);
                Toast.makeText(getApplicationContext(), gajadi, Toast.LENGTH_LONG).show();

                break;
        }


    }
    public void TampilkenLayartancep(View view){
        Intent intent = new Intent(DetailLayarTancep.this, Bookmark.class);
        DetailLayarTancep.this.startActivity(intent);
    }




}