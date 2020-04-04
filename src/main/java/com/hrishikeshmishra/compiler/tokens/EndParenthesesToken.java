package com.hrishikeshmishra.compiler.tokens;

public class EndParenthesesToken extends Token {

    public EndParenthesesToken() {
        super(TokenType.END_PARENTHESES);
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String toString() {
        return "(";
    }
}
