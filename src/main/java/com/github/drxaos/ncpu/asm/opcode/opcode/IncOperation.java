package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class IncOperation extends AbstractUnaryOperation {
    @Override
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.INC_I;
    }
}
