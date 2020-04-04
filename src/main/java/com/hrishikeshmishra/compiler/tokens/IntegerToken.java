package com.hrishikeshmishra.compiler.tokens;

public class IntegerToken extends Token {

    private final Integer value;

    public IntegerToken(Integer value) {
        super(TokenType.INTEGER);
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}

