package com.hrishikeshmishra.compiler.tokens;

public class IntegerToken extends Token<Integer> {

    public IntegerToken(Integer value) {
        super(TokenType.INTEGER, value);
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

}

