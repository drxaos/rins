package com.github.drxaos.ncpu.vm.addr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class Indirect implements Addressing {
    public static final Indirect IMPL = new Indirect();

    @Override
    public int load(NanoMemory mem) {
        return mem.incPcAndReadData();
    }

    @Override
    public void write(NanoMemory mem, int targetAddr, byte data) {
        mem.write(mem.read(targetAddr), data);
    }

    @Override
    public byte read(NanoMemory mem, int targetAddr) {
        return mem.read(mem.read(targetAddr));
    }

    @Override
    public String toName(int address) {
        return "[[" + String.format("%02x", address & 0xff) + "]]";
    }
}
