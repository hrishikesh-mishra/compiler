package com.hrishikeshmishra.compiler.tokens;

public class MinusToken extends Token<Void> {

    public MinusToken() {
        super(TokenType.MINUS, null);
    }

    @Override
    public String toString() {
        return "-";
    }
}
