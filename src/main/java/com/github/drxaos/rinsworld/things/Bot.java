package com.github.drxaos.rinsworld.things;

import com.github.drxaos.rinsworld.Pos;
import com.github.drxaos.rinsworld.ThingType;
import com.github.drxaos.rinsworld.things.Thing;

public class Bot extends Thing {

    public Bot(Pos coord) {
        super(coord);
    }

    @Override
    public ThingType getType() {
        return ThingType.BOT;
    }
}
