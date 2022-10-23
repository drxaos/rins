package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class NotOperation extends AbstractUnaryOperation {
    @Override
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.NOT_I;
    }
}
