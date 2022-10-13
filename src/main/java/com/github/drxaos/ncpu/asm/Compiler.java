package com.github.drxaos.ncpu.asm;

import com.github.drxaos.ncpu.asm.antlr4.NanoasmLexer;
import com.github.drxaos.ncpu.asm.antlr4.NanoasmParser;
import com.github.drxaos.ncpu.asm.ast.AsmBuilder;
import com.github.drxaos.ncpu.asm.ast.AsmCompilationUnit;
import com.github.drxaos.ncpu.asm.ast.AsmErrorListener;
import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import com.github.drxaos.ncpu.asm.opcode.OpcodeTable;
import com.github.drxaos.ncpu.asm.opcode.opcode.AbstractOpcode;
import com.github.drxaos.ncpu.vm.NanoMemory;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;
import java.util.stream.Collectors;

public class Compiler {

    public AsmCompilationUnit parse(String name, String code) {
        final NanoasmLexer lexer = new NanoasmLexer(CharStreams.fromString(code));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final NanoasmParser parser = new NanoasmParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new AsmErrorListener());
        final ParseTree tree = parser.prog();
        final ParseTreeWalker walker = new ParseTreeWalker();
        final AsmBuilder asmBuilder = new AsmBuilder(name, code, parser);
        walker.walk(asmBuilder, tree);
        return asmBuilder.getCompilationUnit();
    }

    public void recognizeOperations(AsmCompilationUnit cu) {
        cu.getInstructions().forEach(instruction -> {
            final String opcode = instruction.getOpcode().toUpperCase();
            try {
                final AbstractOpcode opcodeImpl = OpcodeTable.valueOf(opcode).getOpcode();
                instruction.setOpcodeImpl(opcodeImpl);
            } catch (IllegalArgumentException iae) {
                throw new SyntaxException(new SyntaxError(
                        instruction.getLine(),
                        instruction.getPos(),
                        "Unknown opcode: " + opcode
                ));
            }
        });
    }

    public void buildSymbolsTable(AsmCompilationUnit cu) {
        cu.getInstructions().forEach(instruction ->
                instruction.getOpcodeImpl().fillSymbols(instruction, cu.getSymbolTable()));
    }

    public void recognizeOperands(AsmCompilationUnit cu) {
        cu.getInstructions().forEach(instruction ->
                instruction.getOpcodeImpl().recognizeOperands(instruction, cu.getSymbolTable()));
    }

    public Integer precalculateOffsets(AsmCompilationUnit cu) {
        cu.getInstructions().forEach(instruction -> {
            instruction.setSize(
                    instruction.getOpcodeImpl().precalculateSize(
                            instruction,
                            cu.getSymbolTable()
                    )
            );
        });
        final Integer size = cu.getInstructions().stream().reduce(0, (offset, instruction) -> {
            instruction.setOffset(offset);
            return offset + instruction.getSize();
        }, Integer::sum);
        return size;
    }

    public void resolveReferences(AsmCompilationUnit cu) {
        final List<AsmOperand> notMatched = cu.getSymbolTable().getNotMatched();
        if (!notMatched.isEmpty()) {
            throw new SyntaxException(new SyntaxError(
                    notMatched.get(0).getLine(),
                    notMatched.get(0).getPos(),
                    "Unknown reference: " + notMatched.get(0).getVariable()
            ));
        }
        final List<AsmSymbolTable.Match> matched = cu.getSymbolTable().getMatched();
        matched.forEach(m -> m.getReference().setValue(m.getVariable().getOffset()));
    }

    public void compileInstructions(AsmCompilationUnit cu, NanoMemory nanoMemory) {
        cu.getInstructions().forEach(instruction ->
                instruction.getOpcodeImpl().compile(instruction, nanoMemory));
    }

    public boolean optimizeBranches(AsmCompilationUnit cu) {
        return cu.getInstructions().stream().map(instruction ->
                        instruction.getOpcodeImpl().optimize(instruction, cu.getSymbolTable())
                )
                .collect(Collectors.toSet()).contains(true);
    }
}
