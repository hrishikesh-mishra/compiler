package com.hrishikeshmishra.compiler.tokens;

public class MinusToken extends Token {

    public MinusToken() {
        super(TokenType.MINUS);
    }

    @Override
    public String toString() {
        return "-";
    }
}
