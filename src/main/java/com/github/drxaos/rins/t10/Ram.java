package com.github.drxaos.rins.t10;

import java.util.Arrays;

public class Ram extends Memory {

    public Ram(int slot) {
        super(
                (Math.abs(slot) % 10) * 1000 + 1000,
                (Math.abs(slot) % 10) * 1000 + 1999,
                3,
                3,
                false,
                null
        );
    }

    @Override
    public void initState() {
        Arrays.fill(state, 0);
    }
}
