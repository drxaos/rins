package com.github.drxaos.ncpu.asm;

import com.github.drxaos.ncpu.asm.antlr4.NanoasmLexer;
import com.github.drxaos.ncpu.asm.antlr4.NanoasmParser;
import com.github.drxaos.ncpu.asm.ast.AsmBuilder;
import com.github.drxaos.ncpu.asm.ast.AsmCompilationUnit;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Compiler {

    public AsmCompilationUnit parse(String name, String code) {
        NanoasmLexer lexer = new NanoasmLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        NanoasmParser parser = new NanoasmParser(tokens);
        ParseTree tree = parser.prog();
        ParseTreeWalker walker = new ParseTreeWalker();
        final AsmBuilder asmBuilder = new AsmBuilder(name, code, parser);
        walker.walk(asmBuilder, tree);
        return asmBuilder.getCompilationUnit();
    }
}
