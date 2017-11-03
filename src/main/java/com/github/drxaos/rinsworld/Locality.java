package com.github.drxaos.rinsworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Locality {

    private ArrayList<Thing> things = new ArrayList<>();

    public List<Thing> getThings() {
        return Collections.unmodifiableList(things);
    }

    public void add(Thing thing) {
        things.add(thing);
    }

}
