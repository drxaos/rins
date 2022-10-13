package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class SubOperation extends AbstractBinaryOperation {
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.SUB_II;
    }
}
