package com.hrishikeshmishra.compiler.ast;

import com.hrishikeshmishra.compiler.tokens.Token;

public class NumberExpression extends Expression {

    private final ExpressionType type = ExpressionType.NUMBER;
    private final Token token;

    public NumberExpression(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public ExpressionType getType() {
        return type;
    }


    @Override
    public String toString() {
        return "{\"NumberExpression\" : {" +
                "\"type\" : \"" + type +
                "\", \"token\": \"" + token +
                "\"}}";
    }
}
