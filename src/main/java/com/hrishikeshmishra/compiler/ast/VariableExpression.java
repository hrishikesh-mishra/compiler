package com.hrishikeshmishra.compiler.ast;

import com.hrishikeshmishra.compiler.tokens.Token;

public class VariableExpression extends Expression {

    private final Token<String> variable;

    public VariableExpression(Token<String> variable) {
        super(ExpressionType.VARIABLE);
        this.variable = variable;
    }

    public String getVariable() {
        return variable.getValue();
    }

    @Override
    public String toString() {
        return "{\"VariableExpression\": {" +
                "\"variable\" : \"" + variable +
                "\"}}";
    }
}
