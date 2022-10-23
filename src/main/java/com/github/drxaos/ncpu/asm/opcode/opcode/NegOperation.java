package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class NegOperation extends AbstractUnaryOperation {
    @Override
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.NEG_I;
    }
}
