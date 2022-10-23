package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class VarDirective extends AbstractOpcode {

    public void fillSymbols(AsmInstruction asmInstruction, AsmSymbolTable table) {
        final AsmOperand operand = asmInstruction.getOperands().get(0);
        table.putVariable(operand);
    }

    @Override
    protected int getByte(AsmInstruction asmInstruction, int index, NanoMemory mem) {
        return asmInstruction.getOperands().get(index + 1).getValue();
    }

    @Override
    public int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable table) {
        return asmInstruction.getOperands().size() - 1;
    }

    @Override
    void checkOperands(AsmInstruction asmInstruction, int count) {
        for (int i = 0; i < asmInstruction.getOperands().size(); i++) {
            final AsmOperand operand = asmInstruction.getOperands().get(i);

            if (i == 0 && (!operand.getType().equals(AsmOperand.Type.DIRECT) || operand.getVariable() == null)) {
                throw new SyntaxException(new SyntaxError(
                        operand.getLine(),
                        operand.getPos(),
                        "array name expected: " + operand.getExpression()
                ));
            }

            if (i > 0 && !operand.getType().equals(AsmOperand.Type.LITERAL)) {
                throw new SyntaxException(new SyntaxError(
                        operand.getLine(),
                        operand.getPos(),
                        "literal expected: " + operand.getExpression()
                ));
            }
        }
    }
}
