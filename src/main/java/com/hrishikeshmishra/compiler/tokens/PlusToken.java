package com.hrishikeshmishra.compiler.tokens;

public class PlusToken extends Token {

    public PlusToken() {
        super(TokenType.PLUS);
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String toString() {
        return "+";
    }
}
