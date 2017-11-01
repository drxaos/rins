package com.github.drxaos.rinsworld;

public enum Direction {

    NONE(0, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    int x, y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
