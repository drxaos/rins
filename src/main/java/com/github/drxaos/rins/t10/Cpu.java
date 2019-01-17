package com.github.drxaos.rins.t10;

public class Cpu extends Device {

    private static final int STATE_READ_INS = 0;

    private final int id;
    private int v, pc, state;

    public Cpu(int id) {
        this.id = id;
    }

    @Override
    public void initState() {
        v = 0;
        pc = 100;
        state = 0;
    }

    @Override
    public void handleInput(int addr, int data, int ctrl) {

    }
}
