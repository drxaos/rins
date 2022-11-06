// Generated from /home/xaos/workspace/rins/src/main/antlr4/Eval.g4 by ANTLR 4.10.1
package com.github.drxaos.ncpu.asm.eval.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EvalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EvalVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EvalParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(EvalParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(EvalParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(EvalParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditionOrSubtraction}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionOrSubtraction(EvalParser.AdditionOrSubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicationOrDivision}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationOrDivision(EvalParser.MultiplicationOrDivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Property}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(EvalParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(EvalParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Name}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(EvalParser.NameContext ctx);
}