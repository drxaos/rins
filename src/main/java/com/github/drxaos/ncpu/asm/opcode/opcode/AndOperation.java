package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class AndOperation extends AbstractBinaryOperation {
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.AND_II;
    }
}
