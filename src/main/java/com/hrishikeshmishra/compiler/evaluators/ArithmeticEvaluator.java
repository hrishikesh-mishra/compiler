package com.hrishikeshmishra.compiler.evaluators;

import com.hrishikeshmishra.compiler.ast.*;
import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.tokens.Token;

import java.util.Objects;

import static java.lang.String.format;

public class ArithmeticEvaluator {

    private static final int NEGATIVE_ONE = -1;
    private static final String NULL_EXPRESSION_MSG = "Expression is null.";
    private static final String UNKNOWN_BINARY_OPERATOR_MSG = "Invalid expression. Unknown operator %s ";
    private static final String INVALID_EXPRESSION_TYPE_MSG = "Invalid expression. Expression type is unknown: %s ";

    public int evaluate(Expression node) throws InvalidExpressionException {

        validateNode(node);

        /** TODO: Have some delegation for evaluating different kinds of expression with it grows **/
        switch (node.getType()) {
            case BINARY:
                return evaluateBinaryExpression((BinaryExpression) node);
            case NUMBER:
                return evaluateNumberExpression((NumberExpression) node);
            case PARENTHESES:
                return evaluateParenthesesExpression((ParenthesesExpression) node);
            case NEGATIVE:
                return evaluateNegativeExpression((NegativeExpression) node);
            default:
                throw new InvalidExpressionException(format(INVALID_EXPRESSION_TYPE_MSG, node.getType()));
        }
    }

    private int evaluateNegativeExpression(NegativeExpression node) throws InvalidExpressionException {
        return NEGATIVE_ONE * evaluate(node.getExpression());
    }

    private void validateNode(Expression node) throws InvalidExpressionException {
        if (Objects.isNull(node)) {
            throw new InvalidExpressionException(NULL_EXPRESSION_MSG);
        }
    }

    private int evaluateParenthesesExpression(ParenthesesExpression node) throws InvalidExpressionException {
        return evaluate(node.getExpression());
    }

    private int evaluateNumberExpression(NumberExpression node) {
        return node.getToken().getValue();
    }

    private int evaluateBinaryExpression(BinaryExpression node) throws InvalidExpressionException {
        int leftValue = evaluate(node.getLeft());
        int rightValue = evaluate(node.getRight());
        Token<?> operator = node.getOperator();

        return performArithmeticOperation(operator, leftValue, rightValue);
    }

    private int performArithmeticOperation(Token<?> operator, int left, int right) throws InvalidExpressionException {

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
                throw new InvalidExpressionException(format(UNKNOWN_BINARY_OPERATOR_MSG, operator.getType()));

        }
    }
}
