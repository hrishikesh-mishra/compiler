package com.hrishikeshmishra.compiler.tokens;

public class DivideToken extends Token<Void> {

    public DivideToken() {
        super(TokenType.DIV, null);
    }

    @Override
    public String toString() {
        return "+";
    }
}
