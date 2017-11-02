package com.github.drxaos.rinsworld;

public class Bot extends Thing {

    public Bot(Coord coord) {
        super(coord);
    }

    @Override
    public ThingType getType() {
        return ThingType.BOT;
    }
}
