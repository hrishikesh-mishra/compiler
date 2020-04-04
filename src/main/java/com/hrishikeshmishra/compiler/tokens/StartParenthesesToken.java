package com.hrishikeshmishra.compiler.tokens;

public class StartParenthesesToken extends Token {

    public StartParenthesesToken() {
        super(TokenType.START_PARENTHESES);
    }


    @Override
    public String toString() {
        return "(";
    }
}
