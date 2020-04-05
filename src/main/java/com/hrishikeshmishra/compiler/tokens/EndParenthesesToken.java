package com.hrishikeshmishra.compiler.tokens;

public class EndParenthesesToken extends Token<Void> {

    public EndParenthesesToken() {
        super(TokenType.END_PARENTHESES, null);
    }

    @Override
    public String toString() {
        return "(";
    }
}
