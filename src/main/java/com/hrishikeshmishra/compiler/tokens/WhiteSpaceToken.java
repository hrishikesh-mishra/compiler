package com.hrishikeshmishra.compiler.tokens;

public class WhiteSpaceToken extends Token<String> {

    public WhiteSpaceToken(String value) {
        super(TokenType.WHITESPACE, value);
    }

    @Override
    public String toString() {
        return getValue();
    }

}
