package com.hrishikeshmishra.compiler.ast;

import com.hrishikeshmishra.compiler.tokens.Token;

public class ParenthesesExpression extends Expression {

    private final Token<?> startParentheses;
    private final Expression expression;
    private final Token<?> endParentheses;

    public ParenthesesExpression(Token<?> startParentheses, Expression expression, Token<?> endParentheses) {
        super(ExpressionType.PARENTHESES);
        this.startParentheses = startParentheses;
        this.expression = expression;
        this.endParentheses = endParentheses;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public ExpressionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{\"ParenthesesExpression\" : {" +
                " \"expression\" : " + expression +
                "}}";
    }
}
