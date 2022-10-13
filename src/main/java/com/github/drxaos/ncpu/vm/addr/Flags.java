package com.github.drxaos.ncpu.vm.addr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class Flags implements Addressing {
    public static final Flags IMPL = new Flags();

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
        return mem.read(targetAddr);
    }

    @Override
    public String toName(int address) {
        return "FLAGS [" + String.format("%02x", address) + "]";
    }
}
