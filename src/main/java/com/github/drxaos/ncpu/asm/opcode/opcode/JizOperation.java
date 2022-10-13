package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.vm.NanoInstruction;

public class JizOperation extends AbstractBranchOperation {
    @Override
    protected NanoInstruction getBaseInstruction() {
        return NanoInstruction.JIZ;
    }
}
