package com.hrishikeshmishra.compiler.tokens;

public enum TokenType {

    INTEGER("digit"),
    PLUS("+"),
    MINUS("-"),
    DIV("/"),
    MULTI("*"),
    START_PARENTHESES("("),
    END_PARENTHESES(")"),
    WHITESPACE("whitespace"),
    VARIABLE("variable"),
    ASSIGNMENT("=");


    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
