package com.hrishikeshmishra.compiler.tokens;

import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTI = '*';
    private static final char DIV = '/';
    public static final char ASSIGMENT = '=';
    private static final char START_PARENTHESES = '(';
    private static final char END_PARENTHESES = ')';
    private static final String INVALID_TOKEN_EXCEPTION_MSG = "Invalid Token: '%c' at line: %d, column: %d";
    public static final String INVALID_VARIABLE_EXCEPTION_MSG = "Variable can't be start with digit. line: %d, column: %d";

    private int lineNumber;
    private int columnNumber;
    private final List<String> lines;

    public Tokenizer(List<String> lines) {
        this.lines = lines;
    }

    public List<List<Token<?>>> parse() throws InvalidTokenException {
        isValidLines();

        List<List<Token<?>>> tokens = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            lineNumber = i;
            tokens.add(parse(lines.get(i)));
        }

        return tokens;
    }

    private void isValidLines() throws InvalidTokenException {
        if (lines.isEmpty()) {
            throw new InvalidTokenException("Please provide statement.");
        }
    }

    private List<Token<?>> parse(String expression) throws InvalidTokenException {
        List<Token<?>> tokens = new ArrayList<>();

        columnNumber = 0;
        int strLength = expression.length();

        while (columnNumber < expression.length()) {
            char c = expression.charAt(columnNumber);

            /** TODO: Delegate tokenization through DP when number of token increases.  **/
            if (Character.isDigit(c)) {
                parseDigits(expression, tokens, strLength);
            } else if (Character.isWhitespace(c)) {
                parseWhiteSpaces(expression, tokens, strLength);
            } else if (Character.isLetter(c)) {
                parseVariable(expression, tokens, strLength);
            } else if (c == PLUS) {
                tokens.add(new PlusToken());
                columnNumber++;
            } else if (c == MINUS) {
                tokens.add(new MinusToken());
                columnNumber++;
            } else if (c == MULTI) {
                tokens.add(new MultiplyToken());
                columnNumber++;
            } else if (c == DIV) {
                tokens.add(new DivideToken());
                columnNumber++;
            } else if (c == START_PARENTHESES) {
                tokens.add(new StartParenthesesToken());
                columnNumber++;
            } else if (c == END_PARENTHESES) {
                tokens.add(new EndParenthesesToken());
                columnNumber++;
            } else if (c == ASSIGMENT) {
                tokens.add(new AssignmentToken());
                columnNumber++;
            } else {
                throw new InvalidTokenException(String.format(INVALID_TOKEN_EXCEPTION_MSG, c, lineNumber, columnNumber));
            }
        }

        return tokens;
    }

    private void parseDigits(String text, List<Token<?>> tokens, int strLength) throws InvalidTokenException {
        int startPosition = columnNumber++;

        while (columnNumber < strLength && Character.isLetterOrDigit(text.charAt(columnNumber))) {

            if (Character.isLetter(text.charAt(columnNumber))) {
                throw new InvalidTokenException(String.format(INVALID_VARIABLE_EXCEPTION_MSG, lineNumber, columnNumber));
            }
            columnNumber++;
        }

        String token = text.substring(startPosition, columnNumber);

        /** @//TODO: 04/04/20 handle large numbers **/
        int value = Integer.parseInt(token);
        tokens.add(new IntegerToken(value));
    }

    private void parseVariable(String text, List<Token<?>> tokens, int strLength) {
        int startPosition = columnNumber++;

        while (columnNumber < strLength && Character.isLetterOrDigit(text.charAt(columnNumber))) {
            columnNumber++;
        }

        String token = text.substring(startPosition, columnNumber);
        tokens.add(new VariableToken(token));
    }


    private void parseWhiteSpaces(String text, List<Token<?>> tokens, int strLength) {
        int startPosition = columnNumber++;

        while (columnNumber < strLength && Character.isWhitespace(text.charAt(columnNumber))) {
            columnNumber++;
        }

        String token = text.substring(startPosition, columnNumber);
        /** Ignoring whitespace token for now **/
        //tokens.add(new WhiteSpaceToken(token));
    }


}
