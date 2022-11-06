// Generated from /home/xaos/workspace/rins/src/main/antlr4/Eval.g4 by ANTLR 4.10.1
package com.github.drxaos.ncpu.asm.eval.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EvalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, MUL=4, DIV=5, ADD=6, SUB=7, NUMBER=8, NAME=9, 
		WHITESPACE=10;
	public static final int
		RULE_start = 0, RULE_expression = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'('", "')'", "'*'", "'/'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "MUL", "DIV", "ADD", "SUB", "NUMBER", "NAME", 
			"WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Eval.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EvalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionContext extends ExpressionContext {
		public Token func;
		public ExpressionContext inner;
		public TerminalNode NAME() { return getToken(EvalParser.NAME, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FunctionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberContext extends ExpressionContext {
		public TerminalNode NUMBER() { return getToken(EvalParser.NUMBER, 0); }
		public NumberContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AdditionOrSubtractionContext extends ExpressionContext {
		public ExpressionContext left;
		public Token operator;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(EvalParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(EvalParser.SUB, 0); }
		public AdditionOrSubtractionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterAdditionOrSubtraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitAdditionOrSubtraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitAdditionOrSubtraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplicationOrDivisionContext extends ExpressionContext {
		public ExpressionContext left;
		public Token operator;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(EvalParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(EvalParser.DIV, 0); }
		public MultiplicationOrDivisionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterMultiplicationOrDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitMultiplicationOrDivision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitMultiplicationOrDivision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PropertyContext extends ExpressionContext {
		public Token var;
		public Token prop;
		public List<TerminalNode> NAME() { return getTokens(EvalParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(EvalParser.NAME, i);
		}
		public PropertyContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesesContext extends ExpressionContext {
		public ExpressionContext inner;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesesContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameContext extends ExpressionContext {
		public Token var;
		public TerminalNode NAME() { return getToken(EvalParser.NAME, 0); }
		public NameContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EvalListener ) ((EvalListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EvalVisitor ) return ((EvalVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new NumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(7);
				match(NUMBER);
				}
				break;
			case 2:
				{
				_localctx = new NameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(8);
				((NameContext)_localctx).var = match(NAME);
				}
				break;
			case 3:
				{
				_localctx = new PropertyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(9);
				((PropertyContext)_localctx).var = match(NAME);
				setState(10);
				match(T__0);
				setState(11);
				((PropertyContext)_localctx).prop = match(NAME);
				}
				break;
			case 4:
				{
				_localctx = new FunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(12);
				((FunctionContext)_localctx).func = match(NAME);
				setState(13);
				match(T__1);
				setState(14);
				((FunctionContext)_localctx).inner = expression(0);
				setState(15);
				match(T__2);
				}
				break;
			case 5:
				{
				_localctx = new ParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(17);
				match(T__1);
				setState(18);
				((ParenthesesContext)_localctx).inner = expression(0);
				setState(19);
				match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(31);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(29);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationOrDivisionContext(new ExpressionContext(_parentctx, _parentState));
						((MultiplicationOrDivisionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(23);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(24);
						((MultiplicationOrDivisionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((MultiplicationOrDivisionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(25);
						((MultiplicationOrDivisionContext)_localctx).right = expression(3);
						}
						break;
					case 2:
						{
						_localctx = new AdditionOrSubtractionContext(new ExpressionContext(_parentctx, _parentState));
						((AdditionOrSubtractionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(26);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(27);
						((AdditionOrSubtractionContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AdditionOrSubtractionContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(28);
						((AdditionOrSubtractionContext)_localctx).right = expression(2);
						}
						break;
					}
					} 
				}
				setState(33);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\n#\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u0016"+
		"\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001\u001e\b\u0001\n\u0001\f\u0001!\t\u0001\u0001\u0001"+
		"\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0002\u0001\u0000\u0004\u0005"+
		"\u0001\u0000\u0006\u0007&\u0000\u0004\u0001\u0000\u0000\u0000\u0002\u0015"+
		"\u0001\u0000\u0000\u0000\u0004\u0005\u0003\u0002\u0001\u0000\u0005\u0001"+
		"\u0001\u0000\u0000\u0000\u0006\u0007\u0006\u0001\uffff\uffff\u0000\u0007"+
		"\u0016\u0005\b\u0000\u0000\b\u0016\u0005\t\u0000\u0000\t\n\u0005\t\u0000"+
		"\u0000\n\u000b\u0005\u0001\u0000\u0000\u000b\u0016\u0005\t\u0000\u0000"+
		"\f\r\u0005\t\u0000\u0000\r\u000e\u0005\u0002\u0000\u0000\u000e\u000f\u0003"+
		"\u0002\u0001\u0000\u000f\u0010\u0005\u0003\u0000\u0000\u0010\u0016\u0001"+
		"\u0000\u0000\u0000\u0011\u0012\u0005\u0002\u0000\u0000\u0012\u0013\u0003"+
		"\u0002\u0001\u0000\u0013\u0014\u0005\u0003\u0000\u0000\u0014\u0016\u0001"+
		"\u0000\u0000\u0000\u0015\u0006\u0001\u0000\u0000\u0000\u0015\b\u0001\u0000"+
		"\u0000\u0000\u0015\t\u0001\u0000\u0000\u0000\u0015\f\u0001\u0000\u0000"+
		"\u0000\u0015\u0011\u0001\u0000\u0000\u0000\u0016\u001f\u0001\u0000\u0000"+
		"\u0000\u0017\u0018\n\u0002\u0000\u0000\u0018\u0019\u0007\u0000\u0000\u0000"+
		"\u0019\u001e\u0003\u0002\u0001\u0003\u001a\u001b\n\u0001\u0000\u0000\u001b"+
		"\u001c\u0007\u0001\u0000\u0000\u001c\u001e\u0003\u0002\u0001\u0002\u001d"+
		"\u0017\u0001\u0000\u0000\u0000\u001d\u001a\u0001\u0000\u0000\u0000\u001e"+
		"!\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000\u001f \u0001"+
		"\u0000\u0000\u0000 \u0003\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000"+
		"\u0000\u0003\u0015\u001d\u001f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}