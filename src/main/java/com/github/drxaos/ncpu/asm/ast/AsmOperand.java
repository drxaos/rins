package com.github.drxaos.ncpu.asm.ast;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Data
public class AsmOperand {
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    AsmInstruction asmInstruction;

    int line, pos;
    String expression;

    Type type;
    Integer value;
    String variable;

    public AsmOperand(int line, int pos, String expression) {
        this.line = line;
        this.pos = pos;
        this.expression = expression;
    }

    public enum Type {
        LITERAL(1, 0b10),
        DIRECT(1, 0b01),
        INDIRECT(1, 0b00),
        VARA(0, 0b11),

        BRANCH_SHORT(0, 0);


        @Getter
        private final int size;
        @Getter
        private final int code;

        Type(int size, int code) {
            this.size = size;
            this.code = code;
        }
    }
}
