package com.hrishikeshmishra.compiler.ast;


public abstract class Expression implements Node {

    private final ExpressionType type = ExpressionType.EXPRESSION;

    @Override
    public ExpressionType getType() {
        return type;
    }
}
