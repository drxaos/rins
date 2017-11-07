package com.github.drxaos.rinsworld.things;

import com.github.drxaos.rinsworld.Pos;
import com.github.drxaos.rinsworld.ThingType;

public class Finish extends Thing {
    public Finish(Pos coord) {
        super(coord);
    }

    @Override
    public ThingType getType() {
        return ThingType.FINISH;
    }
}
