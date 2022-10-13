package com.github.drxaos.ncpu.asm.ast;

import com.github.drxaos.ncpu.asm.opcode.opcode.AbstractOpcode;
import lombok.Data;

import java.util.List;

@Data
public class AsmInstruction {
    int line, pos;
    String opcode;
    String expression;
    List<AsmOperand> operands;
    AbstractOpcode opcodeImpl;

    Integer offset;
    Integer size;

    public AsmInstruction(int line, int pos, String opcode, String expression, List<AsmOperand> operands) {
        this.line = line;
        this.pos = pos;
        this.opcode = opcode;
        this.expression = expression;
        this.operands = operands;

        operands.forEach(op -> op.setAsmInstruction(this));
    }
}
