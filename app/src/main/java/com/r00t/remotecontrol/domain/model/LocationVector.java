package com.r00t.remotecontrol.domain.model;

public class LocationVector {
    private int x;
    private int y;

    public LocationVector() {
    }

    public LocationVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
