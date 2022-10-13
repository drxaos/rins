package com.github.drxaos.ncpu.asm.error;

public class SyntaxException extends RuntimeException {
    SyntaxError syntaxError;

    public SyntaxException(SyntaxError syntaxError) {
        super("Syntax error on line " + syntaxError.getLine() + " pos " + syntaxError.getPos() + ": " + syntaxError.getMessage());
        this.syntaxError = syntaxError;
    }
}
