package com.github.drxaos.rins.t10;

public abstract class Memory extends Device {

    protected final int lowAddr;
    protected final int highAddr;
    protected final int[] state;
    protected final int writeLatency;
    protected final int readLatency;
    protected final boolean readOnly;

    public Memory(int lowAddr, int highAddr, int writeLatency, int readLatency, boolean readOnly, int[] data) {
        this.readOnly = readOnly;
        if (highAddr < lowAddr) {
            throw new IllegalArgumentException("negative size of memory");
        }
        int size = highAddr - lowAddr + 1;
        if (data != null && data.length > size) {
            throw new IllegalArgumentException("too much data");
        }
        this.lowAddr = lowAddr;
        this.highAddr = highAddr;
        this.writeLatency = writeLatency;
        this.readLatency = readLatency;
        this.state = new int[size];
        if (data != null) {
            System.arraycopy(data, 0, state, 0, data.length);
        }
    }

    @Override
    public void handleInput(int addr, int data, int ctrl) {
        if (addr < lowAddr && addr > highAddr) {
            return;
        }

        if (ctrl == CTRL_READ) {
            setNextWithLatency(0, state[data - lowAddr], CTRL_RESULT, readLatency);
        } else if (ctrl == CTRL_WRITE && !readOnly) {
            state[addr - lowAddr] = data;
            setNextWithLatency(0, data, CTRL_RESULT, writeLatency);
        }
    }

    public int getLowAddr() {
        return lowAddr;
    }

    public int getHighAddr() {
        return highAddr;
    }

    public int getWriteLatency() {
        return writeLatency;
    }

    public int getReadLatency() {
        return readLatency;
    }

    public int[] raw() {
        return state;
    }

}
