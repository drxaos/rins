package com.github.drxaos.rinsworld;

public class Pos {

    final private int x, y;
    final private Direction direction;

    private Pos(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        if (direction == null) {
            throw new IllegalArgumentException("direction is not nullable");
        }
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getAngle() {
        return direction.angle;
    }

    public static Pos from(int x, int y, Direction direction) {
        return new Pos(x, y, direction);
    }

    public static Pos from(int x, int y) {
        return new Pos(x, y, Direction.RIGHT);
    }
}
