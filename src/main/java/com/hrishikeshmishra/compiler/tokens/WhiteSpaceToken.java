package com.hrishikeshmishra.compiler.tokens;

public class WhiteSpaceToken extends Token {
    private final String value;

    public WhiteSpaceToken(String value) {
        super(TokenType.WHITESPACE);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
