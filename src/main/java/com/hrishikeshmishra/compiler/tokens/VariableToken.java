package com.hrishikeshmishra.compiler.tokens;

public class VariableToken extends Token<String> {

    public VariableToken(String value) {
        super(TokenType.VARIABLE, value);
    }

    @Override
    public String toString() {
        return getValue();
    }
}
