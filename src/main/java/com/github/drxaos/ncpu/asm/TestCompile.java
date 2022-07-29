package com.github.drxaos.ncpu.asm;

import com.github.drxaos.ncpu.asm.ast.AsmCompilationUnit;

public class TestCompile {
    public static void main(String[] args) {
        String code = """
                      ; comment 1
                      start 0
                                            
                      begin:
                        NOP
                        MOV a b ; comment 2
                        MOV asdad xcvxcv ; comment 3
                      JMP begin
                      mov [[27]] 55
                      end: INC x
                      EXP a+= a *bcde
                      HALT
                      """;
        final AsmCompilationUnit compilationUnit = new Compiler().parse("1.asm", code);
        System.out.println(compilationUnit);
    }
}
