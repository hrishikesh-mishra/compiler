package com.hrishikeshmishra.compiler.tokens;

public class MultiplyToken extends Token<Void> {

    public MultiplyToken() {
        super(TokenType.MULTI);
    }

    @Override
    public String toString() {
        return "*";
    }
}
