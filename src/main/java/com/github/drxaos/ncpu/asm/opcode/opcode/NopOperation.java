package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.vm.NanoInstruction;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class NopOperation extends AbstractOpcode {
    public int countOperands(AsmInstruction asmInstruction) {
        return 0;
    }

    @Override
    protected int getByte(AsmInstruction instruction, int index, NanoMemory mem) {
        return NanoInstruction.NOP.getCode();
    }

    @Override
    public int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable symbolTable) {
        return 1;
    }
}
