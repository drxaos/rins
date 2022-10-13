package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class LabelDirective extends AbstractOpcode {
    public int countOperands(AsmInstruction asmInstruction) {
        return 1;
    }

    @Override
    public int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable symbolTable) {
        return 0;
    }

    public void fillSymbols(AsmInstruction asmInstruction, AsmSymbolTable table) {
        final AsmOperand operand = asmInstruction.getOperands().get(0);
        table.putVariable(operand);
    }

    @Override
    protected int getByte(AsmInstruction asmInstruction, int index, NanoMemory mem) {
        return 0;
    }
}
