package com.hrishikeshmishra.compiler.ast;


public abstract class Expression {

    protected final ExpressionType type;

    public Expression(ExpressionType type) {
        this.type = type;
    }

    public ExpressionType getType() {
        return type;
    }

}
