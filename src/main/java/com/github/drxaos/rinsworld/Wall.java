package com.github.drxaos.rinsworld;

public class Wall extends Thing {
    public Wall(Pos coord) {
        super(coord);
    }

    @Override
    public ThingType getType() {
        return ThingType.WALL;
    }
}
