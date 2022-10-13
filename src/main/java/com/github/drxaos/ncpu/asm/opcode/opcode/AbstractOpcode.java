package com.github.drxaos.ncpu.asm.opcode.opcode;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import com.github.drxaos.ncpu.vm.NanoMemory;

import java.util.List;

public abstract class AbstractOpcode {
    public abstract int precalculateSize(AsmInstruction asmInstruction, AsmSymbolTable symbolTable);

    public int countOperands(AsmInstruction asmInstruction) {
        return asmInstruction.getOperands().size();
    }

    public void recognizeOperands(AsmInstruction asmInstruction, AsmSymbolTable table) {
        recognizeOperands(asmInstruction, countOperands(asmInstruction), table);
    }

    void recognizeOperands(AsmInstruction asmInstruction, int operandsCount, AsmSymbolTable table) {
        final List<AsmOperand> operands = asmInstruction.getOperands();
        expectOperands(asmInstruction, operandsCount);
        for (int i = 0; i < operandsCount; i++) {
            recognizeOperand(operands.get(i), table);
        }
        checkOperands(asmInstruction, operandsCount);
    }

    void expectOperands(AsmInstruction asmInstruction, int count) {
        if (asmInstruction.getOperands().size() != count) {
            throw new SyntaxException(new SyntaxError(
                    asmInstruction.getLine(),
                    asmInstruction.getPos(),
                    "operands expected: " + count
            ));
        }
    }

    void checkOperands(AsmInstruction asmInstruction, int count) {
    }

    void recognizeOperand(AsmOperand targetOperand, AsmSymbolTable table) {
        final String targetOperandExpression = targetOperand.getExpression().toLowerCase().strip();

        if (targetOperandExpression.matches("^@$")) {
            targetOperand.setType(AsmOperand.Type.VARA);
            return;
        }

        if (targetOperandExpression.matches("^[-+]?\\d+$")) {
            targetOperand.setType(AsmOperand.Type.LITERAL);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression, 10));
            return;
        }
        if (targetOperandExpression.matches("^[-+]?\\d+[hH]$")) {
            targetOperand.setType(AsmOperand.Type.LITERAL);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[hH]", ""), 16));
            return;
        }
        if (targetOperandExpression.matches("^[-+]?\\d+[bB]$")) {
            targetOperand.setType(AsmOperand.Type.LITERAL);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[bB]", ""), 2));
            return;
        }

        if (targetOperandExpression.matches("^\\[[-+]?\\d+]$")) {
            targetOperand.setType(AsmOperand.Type.DIRECT);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[\\[\\]]", ""), 10));
            return;
        }
        if (targetOperandExpression.matches("^\\[[-+]?\\d+[hH]]$")) {
            targetOperand.setType(AsmOperand.Type.DIRECT);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[\\[\\]hH]", ""), 16));
            return;
        }
        if (targetOperandExpression.matches("^\\[[-+]?\\d+[bB]]$")) {
            targetOperand.setType(AsmOperand.Type.DIRECT);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[\\[\\]bB]", ""), 2));
            return;
        }

        if (targetOperandExpression.matches("^\\[\\[[-+]?\\d+]]$")) {
            targetOperand.setType(AsmOperand.Type.INDIRECT);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[\\[\\]]", ""), 10));
            return;
        }
        if (targetOperandExpression.matches("^\\[\\[[-+]?\\d+[hH]]]$")) {
            targetOperand.setType(AsmOperand.Type.INDIRECT);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[\\[\\]hH]", ""), 16));
            return;
        }
        if (targetOperandExpression.matches("^\\[\\[[-+]?\\d+[bB]]]$")) {
            targetOperand.setType(AsmOperand.Type.INDIRECT);
            targetOperand.setValue(Integer.parseInt(targetOperandExpression.replaceAll("[\\[\\]bB]", ""), 2));
            return;
        }

        if (targetOperandExpression.matches("^[a-zA-Z_][a-zA-Z_0-9]*$")) {
            targetOperand.setType(AsmOperand.Type.DIRECT);
            targetOperand.setVariable(targetOperandExpression);
            table.putReference(targetOperand);
            return;
        }
        if (targetOperandExpression.matches("^\\[[a-zA-Z_][a-zA-Z_0-9]*]$")) {
            targetOperand.setType(AsmOperand.Type.INDIRECT);
            targetOperand.setVariable(targetOperandExpression.replaceAll("[\\[\\]]", ""));
            table.putReference(targetOperand);
            return;
        }
        if (targetOperandExpression.matches("^[a-zA-Z_0-9()\\[\\]+-]+$")) {
            targetOperand.setType(AsmOperand.Type.LITERAL);
            targetOperand.setVariable(targetOperandExpression);
            table.putReference(targetOperand);
            return;
        }

        throw new SyntaxException(new SyntaxError(
                targetOperand.getLine(),
                targetOperand.getPos(),
                "unrecognized operand: " + targetOperandExpression
        ));
    }

    public void fillSymbols(AsmInstruction asmInstruction, AsmSymbolTable table) {
    }

    public void compile(AsmInstruction instruction, NanoMemory nanoMemory) {
        for (int i = 0; i < instruction.getSize(); i++) {
            nanoMemory.write(instruction.getOffset() + i, getByte(instruction, i, nanoMemory));
        }
    }

    protected abstract int getByte(AsmInstruction instruction, int index, NanoMemory mem);

    public boolean optimize(AsmInstruction instruction, AsmSymbolTable table) {
        return false;
    }
}
