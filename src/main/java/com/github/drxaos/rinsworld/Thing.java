package com.github.drxaos.rinsworld;

public abstract class Thing {

    private Pos pos;

    public Thing(Coord coord) {
        this.pos = new Pos(coord);
    }

    public Pos getPos() {
        return pos;
    }

    public abstract ThingType getType();
}
