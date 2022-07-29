package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoMemory;

public class Jiz extends Jmp {
    public static final Jiz IMPL = new Jiz(false);
    public static final Jiz IMPL_N = new Jiz(true);

    final boolean invert;

    public Jiz(boolean invert) {
        this.invert = invert;
    }

    public String getName() {
        return "JIZ";
    }

    public boolean condition(NanoMemory mem) {
        return invert ^ (mem.readFlags() & 0b001) != 0;
    }
}
