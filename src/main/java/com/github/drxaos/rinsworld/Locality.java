package com.github.drxaos.rinsworld;

import java.util.ArrayList;
import java.util.HashMap;

public class Locality {

    ArrayList<Thing> things = new ArrayList<>();
    HashMap<Coord, Thing> map = new HashMap<>();

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
