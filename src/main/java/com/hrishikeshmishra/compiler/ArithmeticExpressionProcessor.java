package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.evaluators.ArithmeticEvaluator;
import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;
import com.hrishikeshmishra.compiler.ast.Expression;
import com.hrishikeshmishra.compiler.parsers.Parser;

public class ArithmeticExpressionProcessor {

    public int process(String expressionStr) throws InvalidTokenException, InvalidExpressionException {

        Parser parser = new Parser(expressionStr);
        Expression expression = parser.parse();


        System.out.printf("\nDebugging Expression Tree:  %s \n", expression);

        ArithmeticEvaluator evaluator = new ArithmeticEvaluator();
        return evaluator.evaluate(expression);
    }
}
