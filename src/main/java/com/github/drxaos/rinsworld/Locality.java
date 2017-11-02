package com.github.drxaos.rinsworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Locality {

    private ArrayList<Thing> things = new ArrayList<>();
    private HashMap<Coord, Thing> map = new HashMap<>();

    public List<Thing> getThings() {
        return Collections.unmodifiableList(things);
    }

    public void add() {

    }

    private void refreshMap() {
        for (Thing thing : things) {
            if (!thing.getPos().isSaved()) {
                resetMap();
                return;
            }
        }
    }

    private void resetMap() {
        map.clear();
        for (Thing thing : things) {
            map.put(thing.getPos().getCoord(), thing);
        }
    }

}
