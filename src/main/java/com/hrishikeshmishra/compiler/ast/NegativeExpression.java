package com.hrishikeshmishra.compiler.ast;

public class NegativeExpression extends Expression {

    private final Expression expression;

    public NegativeExpression(Expression expression) {
        super(ExpressionType.NEGATIVE);

        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "{\"NegativeExpression\": {" +
                "\"type\" : \"" + type +
                "\", \"expression\" : " + expression +
                "}}";
    }
}
