grammar Nanoasm;

prog
   : (line? EOL) + EOF
   ;

line
   : label? instruction? comment?
   ;

instruction
   : opcode arguments?
   ;

opcode
   : TOKEN
   ;

label
   : TOKEN ':'
   ;

arguments
   : argument (argument)*
   ;

argument
   : TOKEN
   ;

comment
   : COMMENT
   ;

TOKEN
   : ~ [\r\n ;:]+
   ;

COMMENT
   : ';' ~ [\r\n]* -> skip
   ;

EOL
   : [\r\n] +
   ;

WS
   : [ \t] -> skip
   ;
