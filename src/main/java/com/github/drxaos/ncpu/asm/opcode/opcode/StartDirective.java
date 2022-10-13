package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class StartDirective extends AbstractOpcode {
    @Override
    public int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable symbolTable) {
        return 0;
    }

    public int countOperands(AsmInstruction asmInstruction) {
        return 0;
    }

    public void compile(AsmInstruction instruction, NanoMemory nanoMemory) {
        nanoMemory.write(nanoMemory.PC, instruction.getOffset());
    }

    @Override
    protected int getByte(AsmInstruction instruction, int index, NanoMemory mem) {
        return 0;
    }
}
