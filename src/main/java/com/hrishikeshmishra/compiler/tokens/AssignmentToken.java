package com.hrishikeshmishra.compiler.tokens;

public class AssignmentToken extends Token<Void> {

    public AssignmentToken() {
        super(TokenType.ASSIGNMENT, null);
    }
}
