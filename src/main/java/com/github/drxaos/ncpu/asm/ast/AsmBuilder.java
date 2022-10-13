package com.github.drxaos.ncpu.asm.ast;

import com.github.drxaos.ncpu.asm.antlr4.NanoasmBaseListener;
import com.github.drxaos.ncpu.asm.antlr4.NanoasmParser;
import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import lombok.Getter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.List;

public class AsmBuilder extends NanoasmBaseListener {
    @Getter
    AsmCompilationUnit compilationUnit;
    String code;
    NanoasmParser parser;

    public AsmBuilder(String name, String code, NanoasmParser parser) {
        this.compilationUnit = new AsmCompilationUnit(name, code);
        this.code = code;
        this.parser = parser;
    }

    @Override
    public void enterLabel(NanoasmParser.LabelContext ctx) {
        compilationUnit.getInstructions().add(new AsmInstruction(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                "$LABEL",
                ctx.getText(),
                List.of(new AsmOperand(
                        ctx.start.getLine(),
                        ctx.start.getCharPositionInLine(),
                        ctx.getText().replace(":", "")
                ))
        ));
    }

    @Override
    public void enterInstruction(NanoasmParser.InstructionContext ctx) {
        final String instr = code.substring(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1);

        final ParseTree opcode = XPath.findAll(ctx, "//opcode", parser).stream()
                .findFirst()
                .orElseThrow(() -> new SyntaxException(new SyntaxError(
                        ctx.start.getLine(),
                        ctx.start.getCharPositionInLine(),
                        "opcode not found in " + instr
                )));

        final ParseTree expression = XPath.findAll(ctx, "//arguments", parser).stream()
                .findFirst()
                .orElse(null);

        final String expr;
        if (expression instanceof NanoasmParser.ArgumentsContext args) {
            expr = code.substring(args.start.getStartIndex(), args.stop.getStopIndex() + 1);
        } else {
            expr = null;
        }

        final List<ParseTree> arguments = XPath.findAll(ctx, "//argument", parser).stream()
                .toList();

        final List<AsmOperand> operands = new ArrayList<>();
        for (ParseTree argument : arguments) {
            if (argument instanceof NanoasmParser.ArgumentContext arg) {
                operands.add(new AsmOperand(
                        arg.start.getLine(),
                        arg.start.getCharPositionInLine(),
                        arg.getText()
                ));
            }
        }
        compilationUnit.getInstructions().add(new AsmInstruction(
                ctx.start.getLine(),
                ctx.start.getCharPositionInLine(),
                opcode.getText(),
                expr,
                operands
        ));
    }
}
