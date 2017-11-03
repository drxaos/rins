package com.github.drxaos.rinsworld;

public class Bot extends Thing {

    public Bot(Pos coord) {
        super(coord);
    }

    @Override
    public ThingType getType() {
        return ThingType.BOT;
    }
}
