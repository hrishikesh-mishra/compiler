package com.hrishikeshmishra.compiler.tokens;

public abstract class Token {
    private final TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public abstract Object getValue();
}
