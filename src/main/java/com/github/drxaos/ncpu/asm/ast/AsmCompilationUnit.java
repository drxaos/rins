package com.github.drxaos.ncpu.asm.ast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsmCompilationUnit {
    String name;
    String code;
    List<AsmInstruction> operations;
}
