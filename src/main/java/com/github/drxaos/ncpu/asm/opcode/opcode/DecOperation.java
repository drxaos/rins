package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class DecOperation extends AbstractUnaryOperation {
    @Override
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.DEC_I;
    }
}
