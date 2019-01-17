package com.github.drxaos.rins.t10;

public class Flash extends Memory {

    public Flash(int slot, int[] data) {
        super(
                (Math.abs(slot) % 10) * 1000 + 1000,
                (Math.abs(slot) % 10) * 1000 + 1999,
                12,
                9,
                false,
                data
        );
    }
}
