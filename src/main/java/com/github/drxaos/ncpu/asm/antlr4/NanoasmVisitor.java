// Generated from /home/xaos/workspace/rins/src/main/antlr4/Nanoasm.g4 by ANTLR 4.10.1
package com.github.drxaos.ncpu.asm.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link NanoasmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface NanoasmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(NanoasmParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(NanoasmParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(NanoasmParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#opcode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpcode(NanoasmParser.OpcodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(NanoasmParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(NanoasmParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(NanoasmParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link NanoasmParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(NanoasmParser.CommentContext ctx);
}