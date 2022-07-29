package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoMemory;

public class JinShort extends JmpShort {
    public static final JinShort IMPL = new JinShort(false);
    public static final JinShort IMPL_N = new JinShort(true);

    final boolean invert;

    public JinShort(boolean invert) {
        this.invert = invert;
    }

    public String getName() {
        return "JIN";
    }

    public boolean condition(NanoMemory mem) {
        return invert ^ (mem.readFlags() & 0b010) != 0;
    }
}
