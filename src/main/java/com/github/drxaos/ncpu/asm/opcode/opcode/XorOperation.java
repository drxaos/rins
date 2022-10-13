package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class XorOperation extends AbstractBinaryOperation {
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.XOR_II;
    }
}
