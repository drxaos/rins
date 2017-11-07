package com.github.drxaos.rinsworld.things;

import com.github.drxaos.rinsworld.Pos;
import com.github.drxaos.rinsworld.ThingType;

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
