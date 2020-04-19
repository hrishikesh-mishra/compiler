package com.hrishikeshmishra.compiler.ast;

import com.hrishikeshmishra.compiler.tokens.Token;

public class BinaryExpression extends Expression {

    private final Expression left;
    private final Token<?> operator;
    private final Expression right;

    public BinaryExpression(Expression left, Token<?> operator, Expression right) {
        super(ExpressionType.BINARY);

        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Token<?> getOperator() {
        return operator;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "{\"BinaryExpression\" : {" +
                "\"type\" : \"" + type +
                "\", \"left\" : " + left +
                ", \"operator\" :\"" + operator +
                "\", \"right\" : " + right +
                "}}";
    }
}
