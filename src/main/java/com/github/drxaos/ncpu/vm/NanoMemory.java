package com.github.drxaos.ncpu.vm;

public class NanoMemory {
    public final int PC;
    public final int FLAGS;
    public final int A;

    public final byte[] block;

    public NanoMemory() {
        int size = 256;
        this.block = new byte[size];

        this.PC = size - 1;
        this.FLAGS = size - 2;
        this.A = size - 3;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(block.length * 2);

        sb.append("    ");
        for (int i = 0; i < 16; i++) {
            sb.append(String.format("_%01x ", i));
        }
        sb.append("\n");
        for (int i = 0; i < block.length; i++) {
            if (i > 0 && i % 16 == 0) {
                sb.append("\n");
            }
            if (i % 16 == 0) {
                sb.append(String.format("%01x_: ", i / 16));
            }
            sb.append(String.format("%02x", block[i]));
            sb.append(" ");
        }
        return sb.toString();
    }
}
