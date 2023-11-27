package com.example.stickhero;

import java.util.ArrayList;

public class GameObjects {
    private double coordinateX;
    private double coordinateY;

    private ArrayList<GameObjects> gameObjects = new ArrayList<>();

    // Existing getters and setters...

    // Method to add objects to the list
    public void addObject(GameObjects gameObject) {
        gameObjects.add(gameObject);
    }


    public double getCoordinateX() {
        return coordinateX;
    }
    public void setCoordinateX(double d) {
        this.coordinateX = d;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double d) {
        this.coordinateY = d;
    }



    private void update() {
    }
}
