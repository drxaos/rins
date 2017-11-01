package com.github.drxaos.rinsworld;

public class Pos {

    private Coord coord;
    private boolean saved = false;

    public Pos(Coord coord) {
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
        saved = false;
    }

    public void add(Coord coord) {
        setCoord(getCoord().add(coord));
    }

    public void sub(Coord coord) {
        setCoord(getCoord().sub(coord));
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved() {
        saved = true;
    }
}
