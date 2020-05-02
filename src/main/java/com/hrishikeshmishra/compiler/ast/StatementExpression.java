package com.hrishikeshmishra.compiler.ast;

import com.hrishikeshmishra.compiler.tokens.Token;

public class StatementExpression extends Expression {

    private final Token<String> lValue;
    private final Expression rValue;

    public StatementExpression(Token<String> lValue, Expression rValue) {
        super(ExpressionType.STATEMENT);
        this.lValue = lValue;
        this.rValue = rValue;
    }

    public String getLValue() {
        return lValue.getValue();
    }

    public Expression getRValue() {
        return rValue;
    }

    @Override
    public String toString() {
        return "{\"StatementExpression\" : {" +
                "\"lValue\" : \"" + lValue +
                "\", \"rValue\" :" + rValue +
                "}}";
    }
}
