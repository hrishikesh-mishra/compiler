package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;

public class Main {

    public static void main(String[] args) throws InvalidTokenException, InvalidExpressionException {

        if (args.length < 0) {
            System.err.println("Please provide expression. ");
            System.exit(-1);
        }

        String exp = args[0];

        ArithmeticExpressionProcessor arithmeticExpressionProcessor = new ArithmeticExpressionProcessor();
        int output = arithmeticExpressionProcessor.process(exp);

        System.out.printf("Given expression: %s  = %d \n", exp, output);
    }

}
