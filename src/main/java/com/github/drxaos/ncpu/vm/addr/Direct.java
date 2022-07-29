package com.github.drxaos.ncpu.vm.addr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class Direct implements Addressing {
    public static final Direct IMPL = new Direct();

    @Override
    public int load(NanoMemory mem) {
        return mem.incPcAndReadData();
    }

    @Override
    public void write(NanoMemory mem, int targetAddr, byte data) {
        mem.write(targetAddr, data);
    }

    @Override
    public byte read(NanoMemory mem, int targetAddr) {
        return mem.read(targetAddr);
    }

    @Override
    public String toName(int address) {
        return "[" + (address & 0xff) + "]";
    }
}
