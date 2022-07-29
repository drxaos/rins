// Generated from /home/xaos/workspace/rins/src/main/antlr4/Nanoasm.g4 by ANTLR 4.10.1
package com.github.drxaos.ncpu.asm.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NanoasmParser}.
 */
public interface NanoasmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(NanoasmParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(NanoasmParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(NanoasmParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(NanoasmParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(NanoasmParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(NanoasmParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#opcode}.
	 * @param ctx the parse tree
	 */
	void enterOpcode(NanoasmParser.OpcodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#opcode}.
	 * @param ctx the parse tree
	 */
	void exitOpcode(NanoasmParser.OpcodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(NanoasmParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(NanoasmParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(NanoasmParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(NanoasmParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(NanoasmParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(NanoasmParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NanoasmParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(NanoasmParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NanoasmParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(NanoasmParser.CommentContext ctx);
}