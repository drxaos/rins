package com.github.drxaos.ncpu.asm.eval;

import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.asm.eval.antlr4.EvalLexer;
import com.github.drxaos.ncpu.asm.eval.antlr4.EvalParser;
import com.github.drxaos.ncpu.asm.eval.antlr4.EvalVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;

public class Eval {
    public static Integer calculate(AsmOperand operand, AsmSymbolTable symbolTable) {
        CharStream chars = CharStreams.fromString(operand.getVariable());
        Lexer lexer = new EvalLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EvalParser parser = new EvalParser(tokens);
        ParseTree tree = parser.start();
        EvalVisitor<Integer> calculator = new EvaluationVisitor(operand, symbolTable);
        return calculator.visit(tree);
    }
}
