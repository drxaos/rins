package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import com.github.drxaos.ncpu.vm.NanoInstruction;
import com.github.drxaos.ncpu.vm.NanoMemory;

public abstract class AbstractShiftOperation extends AbstractOpcode {
    public int countOperands(AsmInstruction asmInstruction) {
        return 2;
    }

    @Override
    void checkOperands(AsmInstruction asmInstruction, int count) {
        checkTargetOperand(asmInstruction, 0);

        final AsmOperand countOperand = asmInstruction.getOperands().get(1);
        if (!countOperand.getType().equals(AsmOperand.Type.LITERAL)) {
            throw new SyntaxException(new SyntaxError(
                    countOperand.getLine(),
                    countOperand.getPos(),
                    "only literals as count supported: " + countOperand.getExpression()
            ));
        }
    }

    @Override
    public int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable symbolTable) {
        return 1 + asmInstruction.getOperands().get(0).getType().getSize() + 1;
    }

    @Override
    protected int getByte(AsmInstruction asmInstruction, int index, NanoMemory mem) {
        return switch (index) {
            case 0 -> getCode(asmInstruction);
            case 1 -> asmInstruction.getOperands().get(0).getType().equals(AsmOperand.Type.VARA)
                    ? getByte(asmInstruction, 2, mem)
                    : asmInstruction.getOperands().get(0).getValue();
            case 2 -> asmInstruction.getOperands().get(1).getValue();
            default -> 0;
        };
    }

    abstract protected NanoInstruction getBaseInstruction();

    protected int getCode(AsmInstruction asmInstruction) {
        return getBaseInstruction().getCode()
               | asmInstruction.getOperands().get(0).getType().getCode() << 2;
    }
}
