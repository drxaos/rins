package com.github.drxaos.ncpu.vm;

public interface Addressing {
    int load(NanoMemory mem);

    void write(NanoMemory mem, int targetAddr, byte data);

    byte read(NanoMemory mem, int targetAddr);

    String toName(int address);
}
