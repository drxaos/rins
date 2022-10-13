package com.github.drxaos.ncpu.vm.addr;

import com.github.drxaos.ncpu.vm.Addressing;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class VarA implements Addressing {
    public static final VarA IMPL = new VarA();

    @Override
    public int load(NanoMemory mem) {
        return mem.A;
    }

    @Override
    public void write(NanoMemory mem, int targetAddr, byte data) {
        mem.write(mem.A, data);
    }

    @Override
    public byte read(NanoMemory mem, int targetAddr) {
        return mem.read(mem.A);
    }

    @Override
    public String toName(int address) {
        return "[" + String.format("%02x", address & 0xff) + "]";
    }
}
