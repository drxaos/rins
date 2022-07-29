package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoMemory;

public class Jin extends Jmp {
    public static final Jin IMPL = new Jin(false);
    public static final Jin IMPL_N = new Jin(true);

    final boolean invert;

    public Jin(boolean invert) {
        this.invert = invert;
    }

    public String getName() {
        return "JIN";
    }

    public boolean condition(NanoMemory mem) {
        return invert ^ (mem.readFlags() & 0b010) != 0;
    }
}
