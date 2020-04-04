package com.hrishikeshmishra.compiler.tokens;

public class StartParenthesesToken extends Token {

    public StartParenthesesToken() {
        super(TokenType.START_PARENTHESES);
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
