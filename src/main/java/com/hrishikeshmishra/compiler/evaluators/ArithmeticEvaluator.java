package com.hrishikeshmishra.compiler.evaluators;

import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.ast.BinaryExpression;
import com.hrishikeshmishra.compiler.ast.Expression;
import com.hrishikeshmishra.compiler.ast.NumberExpression;
import com.hrishikeshmishra.compiler.ast.ParenthesesExpression;
import com.hrishikeshmishra.compiler.tokens.Token;

import java.util.Objects;

public class ArithmeticEvaluator {

    public int evaluate(Expression node) throws InvalidExpressionException {

        validateNode(node);

        switch (node.getType()) {
            case BINARY:
                return evaluateBinaryExpression((BinaryExpression) node);
            case NUMBER:
                return evaluateNumberExpression((NumberExpression) node);
            case PARENTHESES:
                return evaluateParenthesesExpression((ParenthesesExpression) node);
            default:
                throw new InvalidExpressionException("Invalid expression. Expression type is unknown: " + node.getType());
        }
    }

    private void validateNode(Expression node) throws InvalidExpressionException {
        if (Objects.isNull(node)) {
            throw new InvalidExpressionException("Invalid expression. Expression is null");
        }
    }

    private int evaluateParenthesesExpression(ParenthesesExpression node) throws InvalidExpressionException {
        return evaluate(node.getExpression());
    }

    private int evaluateNumberExpression(NumberExpression node) {
        return (int) node.getToken().getValue();
    }

    private int evaluateBinaryExpression(BinaryExpression node) throws InvalidExpressionException {
        int leftValue = evaluate(node.getLeft());
        int rightValue = evaluate(node.getRight());
        Token operator = node.getOperator();

        return performArithmeticOperation(operator, leftValue, rightValue);
    }

    private int performArithmeticOperation(Token operator, int left, int right) throws InvalidExpressionException {

        switch (operator.getType()) {

            case PLUS:
                return left + right;
            case MINUS:
                return left - right;
            case MULTI:
                return left * right;
            case DIV:
                return left / right;

            default:
                throw new InvalidExpressionException("Invalid expression. Unknown operator: " + operator.getType());

        }
    }
}
