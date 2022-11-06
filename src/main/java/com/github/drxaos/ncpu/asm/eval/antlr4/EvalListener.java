// Generated from /home/xaos/workspace/rins/src/main/antlr4/Eval.g4 by ANTLR 4.10.1
package com.github.drxaos.ncpu.asm.eval.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EvalParser}.
 */
public interface EvalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EvalParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(EvalParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link EvalParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(EvalParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Function}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunction(EvalParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Function}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunction(EvalParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumber(EvalParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumber(EvalParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditionOrSubtraction}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditionOrSubtraction(EvalParser.AdditionOrSubtractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditionOrSubtraction}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditionOrSubtraction(EvalParser.AdditionOrSubtractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicationOrDivision}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationOrDivision(EvalParser.MultiplicationOrDivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicationOrDivision}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationOrDivision(EvalParser.MultiplicationOrDivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Property}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterProperty(EvalParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Property}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitProperty(EvalParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(EvalParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parentheses}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(EvalParser.ParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Name}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterName(EvalParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Name}
	 * labeled alternative in {@link EvalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitName(EvalParser.NameContext ctx);
}