package com.github.drxaos.rinsworld;

public class Coord {

    final int x, y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord add(Coord coord) {
        if (coord == null) {
            return this;
        }
        return new Coord(x + coord.x, y + coord.y);
    }

    public Coord sub(Coord coord) {
        if (coord == null) {
            return this;
        }
        return new Coord(x - coord.x, y - coord.y);
    }

    public Coord negative() {
        return new Coord(-x, -y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (x != coord.x) return false;
        return y == coord.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
