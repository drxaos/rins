package com.github.drxaos.ncpu.asm;

import com.github.drxaos.ncpu.asm.ast.AsmCompilationUnit;
import com.github.drxaos.ncpu.vm.NanoCpu;
import com.github.drxaos.ncpu.vm.NanoIo;
import com.github.drxaos.ncpu.vm.NanoMemory;

public class TestCompile {
    public static void main(String[] args) {
        String code = """
                      ; comment 1
                      $var x 1100100b
                      $var y 0
                      ;$array ar 23 64 28 79 66 47 55 12 71 63 86 92 25 41 37 26 96 15 48 73 84

                      $start
                      begin:
                        NOP
                        mov y x
                        mov x 101
                        mov [x] y
                        mov [[101]] 99h

                      ;  MOV i addr(ar)
                         
                      ;  MOV a b ; comment 2
                      ;  MOV asdad xcvxcv ; comment 3
                      JMP incr
                      ;mov [[27]] 55
                      ;end: INC x
                      ;EXP a+= a *bcde
                      end:
                      HALT
                      incr:
                      mov @ 1
                      next:
                      add @ @
                      jio end
                      jmp next
                      """;
        final Compiler compiler = new Compiler();
        final AsmCompilationUnit compilationUnit = compiler.parse("1.asm", code);
        compiler.recognizeOperations(compilationUnit);
        compiler.recognizeOperands(compilationUnit);
        compiler.buildSymbolsTable(compilationUnit);
        final Integer size = compiler.precalculateOffsets(compilationUnit);
        System.out.println("precalculated size: " + size);
        compiler.resolveReferences(compilationUnit);

        while (compiler.optimizeBranches(compilationUnit)) {
            final Integer sizeOptimized = compiler.precalculateOffsets(compilationUnit);
            System.out.println("optimized size: " + sizeOptimized);
            compiler.resolveReferences(compilationUnit);
        }

        final NanoMemory nanoMemory = new NanoMemory();
        compiler.compileInstructions(compilationUnit, nanoMemory);

        System.out.println(compilationUnit);
        System.out.println(nanoMemory);

        final NanoIo io = new NanoIo();
        io.setDebug(true);
        io.setInput(22, (byte) 100);
        final NanoCpu cpu = new NanoCpu(nanoMemory, io);
        cpu.setDebug(true);
        cpu.cycle(200);
    }
}
