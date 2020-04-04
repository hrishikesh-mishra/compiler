package com.hrishikeshmishra.compiler.tokens;

public class DivideToken extends Token {

    public DivideToken() {
        super(TokenType.DIV);
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
