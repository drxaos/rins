package com.github.drxaos.ncpu.asm.ast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsmCompilationUnit {
    String name;
    String code;
    List<AsmInstruction> instructions = new ArrayList<>();
    AsmSymbolTable symbolTable = new AsmSymbolTable();

    public AsmCompilationUnit(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
