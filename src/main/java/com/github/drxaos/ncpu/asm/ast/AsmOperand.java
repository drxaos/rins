package com.github.drxaos.ncpu.asm.ast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsmOperand {
    int line, pos;
    String expression;
}
