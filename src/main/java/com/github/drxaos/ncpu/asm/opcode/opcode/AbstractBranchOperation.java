package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import com.github.drxaos.ncpu.vm.NanoInstruction;
import com.github.drxaos.ncpu.vm.NanoMemory;

public abstract class AbstractBranchOperation extends AbstractOpcode {
    public int countOperands(AsmInstruction asmInstruction) {
        return 1;
    }

    @Override
    void checkOperands(AsmInstruction asmInstruction, int count) {
        final AsmOperand targetOperand = asmInstruction.getOperands().get(0);
        if (targetOperand.getType().equals(AsmOperand.Type.INDIRECT)) {
            throw new SyntaxException(new SyntaxError(
                    targetOperand.getLine(),
                    targetOperand.getPos(),
                    "indirect addressing not supported: " + targetOperand.getExpression()
            ));
        }
    }

    @Override
    protected int getByte(AsmInstruction instruction, int index, NanoMemory mem) {
        final AsmOperand operand = instruction.getOperands().get(0);
        if (operand.getType().equals(AsmOperand.Type.VARA)) {
            return switch (index) {
                case 0 -> getBaseInstruction().getCode();
                case 1 -> mem.A;
                default -> 0;
            };
        } else if (operand.getType().equals(AsmOperand.Type.LITERAL) || operand.getType().equals(AsmOperand.Type.DIRECT)) {
            return switch (index) {
                case 0 -> getBaseInstruction().getCode();
                case 1 -> instruction.getOperands().get(0).getValue();
                default -> 0;
            };
        } else if (operand.getType().equals(AsmOperand.Type.BRANCH_SHORT)) {
            return switch (index) {
                case 0 -> getBaseInstruction().getCode() | ((operand.getValue() - instruction.getOffset()) & 0b1111);
                default -> 0;
            };
        } else {
            throw new SyntaxException(new SyntaxError(
                    operand.getLine(),
                    operand.getPos(),
                    "unknown addressing: " + operand.getExpression()
            ));
        }
    }

    abstract protected NanoInstruction getBaseInstruction();

    @Override
    public int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable symbolTable) {
        return 1 + asmInstruction.getOperands().get(0).getType().getSize();
    }

    public boolean optimize(AsmInstruction instruction, AsmSymbolTable table) {
        final AsmOperand operand = instruction.getOperands().get(0);
        if (operand.getType().equals(AsmOperand.Type.DIRECT) || operand.getType().equals(AsmOperand.Type.LITERAL)) {
            final int offset = operand.getValue() - instruction.getOffset();
            if (-7 <= offset && offset <= 7) {
                operand.setType(AsmOperand.Type.BRANCH_SHORT);
                return true;
            }
        }
        return false;
    }
}
