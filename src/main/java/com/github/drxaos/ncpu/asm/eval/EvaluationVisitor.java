package com.github.drxaos.ncpu.asm.eval;

import com.github.drxaos.ncpu.asm.ast.AsmInstruction;
import com.github.drxaos.ncpu.asm.ast.AsmOperand;
import com.github.drxaos.ncpu.asm.ast.AsmSymbolTable;
import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import com.github.drxaos.ncpu.asm.eval.antlr4.EvalBaseVisitor;
import com.github.drxaos.ncpu.asm.eval.antlr4.EvalParser;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EvaluationVisitor extends EvalBaseVisitor<Integer> {
    AsmOperand operand;
    AsmSymbolTable symbolTable;

    /**
     * Numbers are non-terminal.
     */
    @Override
    public Integer visitNumber(EvalParser.NumberContext ctx) {
        return Integer.parseInt(ctx.NUMBER().getText());
    }

    @Override
    public Integer visitName(EvalParser.NameContext ctx) {
        return symbolTable.getVariable(ctx.var.getText()).getOffset();
    }

    @Override
    public Integer visitProperty(EvalParser.PropertyContext ctx) {
        final AsmInstruction variable = symbolTable.getVariable(ctx.var.getText());
        if (variable == null) {
            throw new SyntaxException(new SyntaxError(
                    operand.getLine(),
                    operand.getPos(),
                    "unknown variable: " + ctx.var.getText()
            ));
        }
        return switch (ctx.prop.getText()) {
            case "offset" -> variable.getOffset();
            case "addr" -> variable.getOffset();
            case "size" -> variable.getSize();
            case "length" -> variable.getSize();
            default -> throw new SyntaxException(new SyntaxError(
                    operand.getLine(),
                    operand.getPos(),
                    "unknown property: " + ctx.getText()
            ));
        };
    }

    @Override
    public Integer visitFunction(EvalParser.FunctionContext ctx) {
        final Integer argument = this.visit(ctx.inner);
        return switch (ctx.func.getText()) {
            case "neg" -> -argument;
            case "not" -> ~argument;
            default -> throw new SyntaxException(new SyntaxError(
                    operand.getLine(),
                    operand.getPos(),
                    "unknown function: " + ctx.getText()
            ));
        };
    }

    /**
     * Parentheses are used to give precedence to
     * the expression around which they are wrapped.
     */
    @Override
    public Integer visitParentheses(EvalParser.ParenthesesContext ctx) {
        return this.visit(ctx.inner);
    }

    @Override
    public Integer visitMultiplicationOrDivision(EvalParser.MultiplicationOrDivisionContext ctx) {
        if (ctx.operator.getText().equals("*")) {
            return this.visit(ctx.left) * this.visit(ctx.right);
        }

        return this.visit(ctx.left) / this.visit(ctx.right);
    }

    @Override
    public Integer visitAdditionOrSubtraction(EvalParser.AdditionOrSubtractionContext ctx) {
        if (ctx.operator.getText().equals("+")) {
            return this.visit(ctx.left) + this.visit(ctx.right);
        }

        return this.visit(ctx.left) - this.visit(ctx.right);
    }
}
