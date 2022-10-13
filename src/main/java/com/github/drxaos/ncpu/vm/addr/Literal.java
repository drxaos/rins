package com.github.drxaos.ncpu.vm.addr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class Literal implements Addressing {
    public static final Literal IMPL = new Literal();

    @Override
    public int load(NanoMemory mem) {
        return mem.incPcAndReadData();
    }

    @Override
    public void write(NanoMemory mem, int targetAddr, byte data) {
        // ignore
    }

    @Override
    public byte read(NanoMemory mem, int targetAddr) {
        return (byte) targetAddr;
    }

    @Override
    public String toName(int address) {
        return "" + String.format("%02x", address) + "";
    }
}
