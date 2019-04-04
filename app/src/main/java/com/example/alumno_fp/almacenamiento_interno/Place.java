package com.example.alumno_fp.almacenamiento_interno;

public class Place {

    private String name;

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                '}';
    }
}
