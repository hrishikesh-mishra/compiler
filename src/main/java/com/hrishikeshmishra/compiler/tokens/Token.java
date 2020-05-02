package com.hrishikeshmishra.compiler.tokens;

public abstract class Token<T> {

    private final TokenType type;
    private final T value;

    public Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    public Token(TokenType type, T value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

}
