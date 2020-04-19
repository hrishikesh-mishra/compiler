package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvalidTokenException, InvalidExpressionException {

        if (args.length < 0) {
            System.err.println("Please provide expression. ");
            System.exit(-1);
        }

        String exp = args[0];
        List<String> lines = getLines(exp);

        ArithmeticExpressionProcessor arithmeticExpressionProcessor = new ArithmeticExpressionProcessor();
        int output = arithmeticExpressionProcessor.process(lines);

        System.out.printf("\nGiven expression: %s\nAnd Result = %d \n", exp, output);
    }

    public static List<String> getLines(String expressionsStr) {

        List<String> lines = new ArrayList<>();
        for (String line : expressionsStr.split(";")) {
            if (!line.trim().isEmpty()) {
                lines.add(line.trim());
            }
        }
        return lines;
    }
}
