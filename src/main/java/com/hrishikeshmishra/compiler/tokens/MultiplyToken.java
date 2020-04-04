package com.hrishikeshmishra.compiler.tokens;

public class MultiplyToken extends Token {

    public MultiplyToken() {
        super(TokenType.MULTI);
    }


    @Override
    public String toString() {
        return "*";
    }
}
