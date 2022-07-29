package com.github.drxaos.ncpu.vm.instr;

import com.github.drxaos.ncpu.vm.NanoMemory;

public class JizShort extends JmpShort {
    public static final JizShort IMPL = new JizShort(false);
    public static final JizShort IMPL_N = new JizShort(true);

    final boolean invert;

    public JizShort(boolean invert) {
        this.invert = invert;
    }

    public String getName() {
        return "JIZ";
    }

    public boolean condition(NanoMemory mem) {
        return invert ^ (mem.readFlags() & 0b001) != 0;
    }
}
