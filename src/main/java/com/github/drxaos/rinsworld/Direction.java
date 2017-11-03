package com.github.drxaos.rinsworld;

public enum Direction {

    UP(0, -1, Math.PI * 3 / 2),
    DOWN(0, 1, Math.PI * 1 / 2),
    LEFT(-1, 0, Math.PI),
    RIGHT(1, 0, 0);

    final int x;
    final int y;
    final double angle;

    Direction(int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
}
