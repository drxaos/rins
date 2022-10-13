package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class MovOperation extends AbstractBinaryOperation {
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.MOV_II;
    }
}
