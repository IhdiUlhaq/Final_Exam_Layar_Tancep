package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.uts.Adapter.AdapterListTancep;
import com.example.uts.model.LayarTancep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListLayarActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final List<LayarTancep> viewItems = new ArrayList<>();

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.Tancep_list)
    RecyclerView tancepList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layar);
        ButterKnife.bind(this);

        tancepList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tancepList.setHasFixedSize(true);
        tancepList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AdapterListTancep adapterListTancep = new AdapterListTancep(this, viewItems);
        tancepList.setAdapter(adapterListTancep);

        addItemsFromJSON();
    }

    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject itemObj = jsonArray.getJSONObject(i);

                LayarTancep layarTancep = new LayarTancep();
                layarTancep.setOverview(itemObj.getString("overview"));
                layarTancep.setPosterPath(itemObj.getString("poster_path"));
                layarTancep.setTitle(itemObj.getString("title"));
                layarTancep.setReleaseDate(itemObj.getString("release_date"));
                layarTancep.setOriginalTitle(itemObj.getString("original_title"));

                viewItems.add(layarTancep);
            }
        } catch (IOException | JSONException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {

            String jsonString;
            inputStream = getResources().openRawResource(R.raw.list_movie);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );


            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(stringBuilder);
    }
}