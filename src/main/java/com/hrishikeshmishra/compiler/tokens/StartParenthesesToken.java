package com.hrishikeshmishra.compiler.tokens;

public class StartParenthesesToken extends Token<Void> {

    public StartParenthesesToken() {
        super(TokenType.START_PARENTHESES, null);
    }

    @Override
    public String toString() {
        return "(";
    }
}
