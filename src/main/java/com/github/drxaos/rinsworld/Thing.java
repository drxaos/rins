package com.github.drxaos.rinsworld;

public abstract class Thing {

    private Pos pos;

    public Thing(Pos pos) {
        this.pos = pos;
    }

    public Pos getPos() {
        return pos;
    }

    public abstract ThingType getType();
}
