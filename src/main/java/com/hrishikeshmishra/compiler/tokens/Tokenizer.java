package com.hrishikeshmishra.compiler.tokens;

import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTI = '*';
    private static final char DIV = '/';
    private static final char START_PARENTHESES = '(';
    private static final char END_PARENTHESES = ')';
    private static final String INVALID_TOKEN_EXCEPTION_MSG = "Invalid Token: '%c' at position: '%d'";

    public List<Token<?>> parse(String expression) throws InvalidTokenException {
        List<Token<?>> tokens = new ArrayList<>();

        int index = 0;
        int strLength = expression.length();

        while (index < expression.length()) {
            char c = expression.charAt(index);

            /** TODO: Delegate tokenization through DP when number of token increases.  **/
            if (Character.isDigit(c)) {
                index = parseDigits(expression, tokens, index, strLength);
            } else if (Character.isWhitespace(c)) {
                index = parseWhiteSpaces(expression, tokens, index, strLength);
            } else if (c == PLUS) {
                tokens.add(new PlusToken());
                index++;
            } else if (c == MINUS) {
                tokens.add(new MinusToken());
                index++;
            } else if (c == MULTI) {
                tokens.add(new MultiplyToken());
                index++;
            } else if (c == DIV) {
                tokens.add(new DivideToken());
                index++;
            } else if (c == START_PARENTHESES) {
                tokens.add(new StartParenthesesToken());
                index++;
            } else if (c == END_PARENTHESES) {
                tokens.add(new EndParenthesesToken());
                index++;
            } else {
                throw new InvalidTokenException(String.format(INVALID_TOKEN_EXCEPTION_MSG, c, index));
            }
        }

        return tokens;
    }

    private int parseDigits(String text, List<Token<?>> tokens, int index, int strLength) {
        int startPosition = index++;

        while (index < strLength && Character.isDigit(text.charAt(index))) {
            index++;
        }

        String token = text.substring(startPosition, index);

        /** @//TODO: 04/04/20 handle large numbers **/
        int value = Integer.parseInt(token);
        tokens.add(new IntegerToken(value));
        return index;
    }

    private int parseWhiteSpaces(String text, List<Token<?>> tokens, int index, int strLength) {
        int startPosition = index++;

        while (index < strLength && Character.isWhitespace(text.charAt(index))) {
            index++;
        }

        String token = text.substring(startPosition, index);
        /** Ignoring whitespace token for now **/
        //tokens.add(new WhiteSpaceToken(token));
        return index;
    }


}
