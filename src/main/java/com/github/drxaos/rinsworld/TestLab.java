package com.github.drxaos.rinsworld;

import com.github.drxaos.rinsworld.things.Finish;
import com.github.drxaos.rinsworld.things.Start;
import com.github.drxaos.rinsworld.things.Wall;

public class TestLab extends Locality {
    public TestLab() {
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                if (Math.abs(x) == 2 || Math.abs(y) == 2) {
                    add(new Wall(Pos.from(x, y)));
                }
            }
        }
        add(new Start(Pos.from(-1, -1)));
        add(new Finish(Pos.from(1, 1)));
    }
}
