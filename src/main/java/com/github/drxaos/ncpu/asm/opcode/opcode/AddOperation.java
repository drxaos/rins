package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class AddOperation extends AbstractBinaryOperation {
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.ADD_II;
    }
}
