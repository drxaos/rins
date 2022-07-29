package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoMemory;

public class JioShort extends JmpShort {
    public static final JioShort IMPL = new JioShort(false);
    public static final JioShort IMPL_N = new JioShort(true);

    final boolean invert;

    public JioShort(boolean invert) {
        this.invert = invert;
    }

    public String getName() {
        return "JIO";
    }

    public boolean condition(NanoMemory mem) {
        return invert ^ (mem.readFlags() & 0b100) != 0;
    }
}
