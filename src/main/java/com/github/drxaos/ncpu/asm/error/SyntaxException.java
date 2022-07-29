package com.github.drxaos.ncpu.asm.error;

public class SyntaxException extends RuntimeException {
    SyntaxError syntaxError;

    public SyntaxException(SyntaxError syntaxError) {
        this.syntaxError = syntaxError;
    }
}
