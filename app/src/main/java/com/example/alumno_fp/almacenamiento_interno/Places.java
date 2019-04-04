package com.example.alumno_fp.almacenamiento_interno;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Places {

    private ArrayList<Place> placesList;

    public Places() {
        placesList = new ArrayList<>();
    }

    public ArrayList<Place> getPlaces() {
        return placesList;
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Places fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,Places.class);
    }

    public void addPlace(Place place){
        placesList.add(place);
    }

    public void removePlace(Place place){
        placesList.remove(place);
    }

    @Override
    public String toString() {
        return "Places{" +
                "placesList=" + placesList +
                '}';
    }
}
