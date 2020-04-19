package com.hrishikeshmishra.compiler.parsers;

import com.hrishikeshmishra.compiler.ast.*;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;
import com.hrishikeshmishra.compiler.tokens.Token;
import com.hrishikeshmishra.compiler.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse is Recursive Descent Parser
 * And its based on following Grammar
 *
 * <pre>
 *
 *      Statement -> Variable Assign Expression
 *      Expression -> [-] Term { PlusMinusOperation Term }
 *      Term ->  Factor { MultiDiv Factor }
 *      Factor ->  (Expression) | Number | Variable
 *      PlusMinusOperation  ->  + | -
 *      MultiDiv ->  * | /
 *      Number -> 0 - 9 { 0-9 }
 *      Variable ->  A-Z | a-z { A-Z| a-z | 0-9 }
 *      Assign -> =
 *
 *      Here,
 *      {} -> Represent the repetition of zero more times.
 *      [] -> Represent optional
 *
 *
 * <pre>
 */
public class Parser {

    private static final String INVALID_TOKEN_EXCEPTION = "Invalid Token " +
            "Expression at position: %d and expected token was: %s";

    private final List<List<Token<?>>> tokens;
    private int position;
    private int lineNumber;

    public Parser(List<List<Token<?>>> tokens) {
        this.tokens = tokens;
    }

    public List<Expression> parse() throws InvalidTokenException {

        List<Expression> expressions = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            this.position = 0;
            expressions.add(parseStatement());
            this.lineNumber++;
        }

        return expressions;
    }

    /**
     * This function is based on following production rule: Variable Assign Expression
     *
     * @return
     * @throws InvalidTokenException
     */
    private Expression parseStatement() throws InvalidTokenException {
        Token<String> lValue = (Token<String>) match(TokenType.VARIABLE);
        Token<String> assignment = (Token<String>) match(TokenType.ASSIGNMENT);
        Expression rValue = parseExpression();

        return new StatementExpression(lValue, rValue);
    }

    /**
     * This function is based on following production rule: Expression -> [-] Term { PlusMinusOperation Term }
     *
     * @return
     * @throws InvalidTokenException
     */
    private Expression parseExpression() throws InvalidTokenException {

        boolean isNegativeNumber = false;

        if (getCurrentToken().getType() == TokenType.MINUS) {
            isNegativeNumber = true;
            nextToken();
        }

        Expression left = parseTerm();

        if (isNegativeNumber) {
            left = new NegativeExpression(left);
        }

        while (!consumedAll() &&
                (getCurrentToken().getType() == TokenType.PLUS ||
                        getCurrentToken().getType() == TokenType.MINUS)) {

            Token<?> operator = nextToken();
            Expression right = parseTerm();
            left = new BinaryExpression(left, operator, right);
        }

        return left;
    }

    /**
     * This processor is based on following production rule: Term ->  Factor { MultiDiv Factor }
     *
     * @return
     * @throws InvalidTokenException
     */
    private Expression parseTerm() throws InvalidTokenException {
        Expression left = parseFactor();

        while (!consumedAll() &&
                (getCurrentToken().getType() == TokenType.MULTI ||
                        getCurrentToken().getType() == TokenType.DIV)) {

            Token<?> operator = nextToken();
            Expression right = parseFactor();
            left = new BinaryExpression(left, operator, right);
        }

        return left;
    }

    /**
     * This processor is based on following production rule: Factor ->  (Expression) | Number | Variable
     *
     * @return
     * @throws InvalidTokenException
     */
    private Expression parseFactor() throws InvalidTokenException {

        if (!consumedAll() && getCurrentToken().getType() == TokenType.START_PARENTHESES) {
            Token<?> startToken = nextToken();
            Expression expression = parseExpression();
            Token<?> endToken = match(TokenType.END_PARENTHESES);
            return new ParenthesesExpression(startToken, expression, endToken);
        }

        if (!consumedAll() && getCurrentToken().getType() == TokenType.VARIABLE) {
            Token<String> token = (Token<String>) match(TokenType.VARIABLE);
            return new VariableExpression(token);
        }

        Token<Integer> token = (Token<Integer>) match(TokenType.INTEGER);
        return new NumberExpression(token);
    }

    private boolean consumedAll() {
        return position >= tokens.get(lineNumber).size();
    }

    private Token<?> nextToken() {
        Token<?> token = tokens.get(lineNumber).get(position);
        position++;
        return token;
    }

    private Token<?> getCurrentToken() {
        return tokens.get(lineNumber).get(position);
    }

    private Token<?> match(TokenType type) throws InvalidTokenException {
        if (!consumedAll() && getCurrentToken().getType() == type) {
            return nextToken();
        }
        throw new InvalidTokenException(String.format(INVALID_TOKEN_EXCEPTION, position, type));
    }

}



