package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class RorOperation extends AbstractShiftOperation {
    @Override
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.ROR_I;
    }
}
