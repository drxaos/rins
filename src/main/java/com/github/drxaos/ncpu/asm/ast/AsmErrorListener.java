package com.github.drxaos.ncpu.asm.ast;

import com.github.drxaos.ncpu.asm.error.SyntaxError;
import com.github.drxaos.ncpu.asm.error.SyntaxException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class AsmErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e
    ) {
        if (offendingSymbol instanceof Token token) {
            throw new SyntaxException(new SyntaxError(
                    line,
                    charPositionInLine,
                    "unexpected input '" + token.getText() + "'"
            ));
        } else {
            throw new SyntaxException(new SyntaxError(
                    line,
                    charPositionInLine,
                    msg
            ));
        }
    }
}
