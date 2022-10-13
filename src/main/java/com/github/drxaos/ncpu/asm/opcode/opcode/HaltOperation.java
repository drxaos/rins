package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.vm.NanoInstruction;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class HaltOperation extends AbstractOpcode {
    public int countOperands(AsmInstruction asmInstruction) {
        return 0;
    }

    @Override
    public int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable symbolTable) {
        return 1;
    }

    @Override
    protected int getByte(AsmInstruction asmInstruction, int index, NanoMemory mem) {
        return NanoInstruction.HALT.getCode();
    }
}
