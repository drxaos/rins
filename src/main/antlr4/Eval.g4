// https://github.com/arothuis/antlr4-calculator
grammar Eval;

/*
 * Tokens (terminal)
 */
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
NUMBER: '-'?[0-9]+;
NAME: [a-zA-Z_][a-zA-Z0-9_]*;
WHITESPACE: [ \r\n\t]+ -> skip;

/*
 * Production Rules (non-terminal)
 */
start : expression;

/*
 * The order in which expressions are evaluated
 * is determined by the order in which possible
 * matching rules are defined.
 * Here, numbers are dealt with first, then parentheses
 * and so on.
 *
 * Multiplication and division are on the
 * same precedence level, so they are grouped.
 * The same goes for addition and subtraction.
 *
 * Labels (e.g. "# Parentheses") are added to each rule
 * to provide context to which rule is being parsed.
 * This is can be used in a Listener or Visitor
 * to allow for separate control over Listener or Visitor actions.
 *
 * Likewise, inner labels (e.g. "left=expression")
 * can be added to child nodes of the rule.
 * This makes them identifiable in a
 * Listener's or Visitor's parsing of the rule,
 * allowing for even more fine-grained control.
 */
expression
   : NUMBER                                               # Number
   | var=NAME                                             # Name
   | var=NAME '.' prop=NAME                               # Property
   | func=NAME '(' inner=expression ')'                   # Function
   | '(' inner=expression ')'                             # Parentheses
   | left=expression operator=(MUL|DIV) right=expression  # MultiplicationOrDivision
   | left=expression operator=(ADD|SUB) right=expression  # AdditionOrSubtraction
   ;
