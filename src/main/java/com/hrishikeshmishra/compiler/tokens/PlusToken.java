package com.hrishikeshmishra.compiler.tokens;

public class PlusToken extends Token {

    public PlusToken() {
        super(TokenType.PLUS, null);
    }

    @Override
    public String toString() {
        return "+";
    }
}
