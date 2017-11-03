package com.github.drxaos.rinsworld;

public class TestLab extends Locality {
    public TestLab() {
        add(new Wall(Pos.from(0, 0)));
        add(new Wall(Pos.from(2, 3)));
        add(new Wall(Pos.from(-5, 4)));
    }
}
