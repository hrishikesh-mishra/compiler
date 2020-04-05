package com.hrishikeshmishra.compiler.ast;

import com.hrishikeshmishra.compiler.tokens.Token;

public class NumberExpression extends Expression {

    private final Token<Integer> token;

    public NumberExpression(Token<Integer> token) {
        super(ExpressionType.NUMBER);
        this.token = token;
    }

    public Integer getValue() {
        return token.getValue();
    }

    @Override
    public String toString() {
        return "{\"NumberExpression\" : {" +
                "\"type\" : \"" + type +
                "\", \"token\": \"" + token +
                "\"}}";
    }
}
