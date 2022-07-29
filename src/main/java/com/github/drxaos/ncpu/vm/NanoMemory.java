package com.github.drxaos.ncpu.vm;

public class NanoMemory {
    public final int PC;
    public final int FLAGS;
    public final int A;
    public final int B;

    public final byte[] block;

    public NanoMemory() {
        int size = 256;
        this.block = new byte[size];

        this.PC = size - 1;
        this.FLAGS = size - 2;
        this.A = size - 3;
        this.B = size - 4;
    }

    public byte read(int address) {
        return block[address & 0xFF];
    }

    public void write(int address, byte data) {
        block[address & 0xFF] = data;
    }

    public void write(int address, int data) {
        block[address & 0xFF] = (byte) data;
    }

    public void writeFlags(byte data) {
        block[FLAGS] = data;
    }

    public byte readFlags() {
        return block[FLAGS];
    }

    public byte pc() {
        return block[PC];
    }

    public byte incPc() {
        return ++block[PC];
    }

    public byte incPcAndReadData() {
        return block[incPc() & 0xFF];
    }
}
