package com.hrishikeshmishra.compiler.parsers;

import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;
import com.hrishikeshmishra.compiler.ast.BinaryExpression;
import com.hrishikeshmishra.compiler.ast.Expression;
import com.hrishikeshmishra.compiler.ast.NumberExpression;
import com.hrishikeshmishra.compiler.ast.ParenthesesExpression;
import com.hrishikeshmishra.compiler.tokens.Token;
import com.hrishikeshmishra.compiler.tokens.TokenType;
import com.hrishikeshmishra.compiler.tokens.Tokenizer;

import java.util.List;

/**
 * Parse is Recursive Descent Parser
 * And its based on following Grammar
 *
 * <pre>
 *
 *      Expression -> Term { PlusMinusOperation Term }
 *      Term ->  Factor { MultiDiv Factor }
 *      Factor ->  (Expression) | Number
 *      PlusMinusOperation  ->  + | -
 *      MultiDiv ->  * | /
 *      Number → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 *
 *      Here {} -> present the repetition of zero more times.
 *
 * <pre>
 */
public class Parser {

    private static final String INVALID_TOKEN_EXCEPTION = "Invalid Token " +
            "Expression at position: %d and expected token was: %s";

    private final List<Token> tokens;
    private final Tokenizer tokenizer;

    private int position;

    public Parser(String expression) throws InvalidTokenException {
        this.tokenizer = new Tokenizer();
        this.tokens = tokenizer.parse(expression);
        this.position = 0;
    }

    public Expression parse() throws InvalidTokenException {
        return parseExpression();
    }

    /**
     * This function is based on following production rule: Expression -> Term { PlusMinusOperation Term }
     *
     * @return
     * @throws InvalidTokenException
     */
    private Expression parseExpression() throws InvalidTokenException {
        Expression left = parseTerm();

        while (!consumedAll() &&
                (getCurrentToken().getType() == TokenType.PLUS ||
                        getCurrentToken().getType() == TokenType.MINUS)) {

            Token operator = nextToken();
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

            Token operator = nextToken();
            Expression right = parseFactor();
            left = new BinaryExpression(left, operator, right);
        }

        return left;
    }

    /**
     * This processor is based on following production rule: Factor ->  (Expression) | Number
     *
     * @return
     * @throws InvalidTokenException
     */
    private Expression parseFactor() throws InvalidTokenException {

        if (!consumedAll() && getCurrentToken().getType() == TokenType.START_PARENTHESES) {
            Token startToken = nextToken();
            Expression expression = parseExpression();
            Token endToken = match(TokenType.END_PARENTHESES);
            return new ParenthesesExpression(startToken, expression, endToken);
        }

        Token token = match(TokenType.INTEGER);
        return new NumberExpression(token);
    }

    private boolean consumedAll() {
        return position >= tokens.size();
    }

    private Token nextToken() {
        Token token = tokens.get(position);
        position++;
        return token;
    }

    private Token getCurrentToken() {
        return tokens.get(position);
    }

    private Token match(TokenType type) throws InvalidTokenException {
        if (!consumedAll() && getCurrentToken().getType() == type) {
            return nextToken();
        }
        throw new InvalidTokenException(String.format(INVALID_TOKEN_EXCEPTION, position, type));
    }

}


