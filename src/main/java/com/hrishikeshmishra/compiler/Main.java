package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;

public class Main {

    public static void main(String[] args) throws InvalidTokenException, InvalidExpressionException {

        String exp = "(1*2)-4+5)";
        ArithmeticExpressionProcessor arithmeticExpressionProcessor = new ArithmeticExpressionProcessor();
        int output = arithmeticExpressionProcessor.process(exp);

        System.out.println(output);
    }

}
