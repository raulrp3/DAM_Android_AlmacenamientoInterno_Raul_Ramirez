package com.example.alumno_fp.almacenamiento_interno;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    RecyclerView listPlaces;
    Button buttonPlaces;
    Places placesList;
    PlaceAdapter mAdapter;
    private final int CODE_SAVE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initAdapter();
    }

    private void initUI(){
        buttonPlaces = findViewById(R.id.button_places);
        placesList = new Places();

        listPlaces = findViewById(R.id.list_places);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listPlaces.setLayoutManager(llm);
        listPlaces.setHasFixedSize(true);

        if (!readPlaces().isEmpty()){
            placesList = placesList.fromJson(readPlaces());
        }

        buttonPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SaveActivity.class);
                startActivityForResult(intent,CODE_SAVE);
            }
        });
    }

    private void initAdapter(){
        mAdapter = new PlaceAdapter(placesList.getPlaces(),this);
        listPlaces.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == CODE_SAVE){
            String place = data.getStringExtra("Place");
            placesList.addPlace(new Place(place));
            mAdapter.notifyDataSetChanged();

            String json = placesList.toJson();
            writePlaces(json);
        }
    }

    private String readPlaces(){
        String places =  "";
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("places.txt")));
            places = reader.readLine();
            reader.close();
        }catch (Exception ex){
            Log.e("Error",ex.getMessage());
        }

        return places;
    }

    private void writePlaces(String places){
        try{
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput("places.txt", Context.MODE_PRIVATE));
            writer.write(places);
            writer.close();
        }catch (Exception ex){
            Log.e("Error",ex.getMessage());
        }
    }
}
