package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoMemory;

public class Jio extends Jmp {
    public static final Jio IMPL = new Jio(false);
    public static final Jio IMPL_N = new Jio(true);

    final boolean invert;

    public Jio(boolean invert) {
        this.invert = invert;
    }

    public String getName() {
        return "JIO";
    }

    public boolean condition(NanoMemory mem) {
        return invert ^ (mem.readFlags() & 0b100) != 0;
    }
}
