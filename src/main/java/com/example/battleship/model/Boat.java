package com.example.battleship.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Boat implements Serializable{
    private int lenght,placementX,placementY;
    private boolean vertical;

    public Boat(int lenght) {
        this.lenght = lenght;
        this.vertical = true;
    }


    public void setPlacementX(int placementX, int placementY) {
        this.placementX = placementX;
        this.placementY = placementY;
    }

    public int getPlacementX() {
        return placementX;
    }

    public int getPlacementY() {
        return placementY;
    }

    public void setVertical() {
        this.vertical = true;
    }

    public void setHorizontal() {
        this.vertical = false;
    }

    public boolean isVertical() {
        return vertical;
    }

    public int getLenght() {
        return lenght;
    }
}
