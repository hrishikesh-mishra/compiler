package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.evaluators.ArithmeticEvaluator;
import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;
import com.hrishikeshmishra.compiler.ast.Expression;
import com.hrishikeshmishra.compiler.parsers.Parser;
import com.hrishikeshmishra.compiler.tokens.Token;
import com.hrishikeshmishra.compiler.tokens.Tokenizer;

import java.util.List;

public class ArithmeticExpressionProcessor {

    public int process(List<String> lines) throws InvalidTokenException, InvalidExpressionException {
        Tokenizer tokenizer = new Tokenizer(lines);
        List<List<Token<?>>> tokens = tokenizer.parse();

        Parser parser = new Parser(tokens);
        List<Expression> expressions = parser.parse();


        System.out.printf("\nDebugging Expression Tree:  %s \n", expressions);

        ArithmeticEvaluator evaluator = new ArithmeticEvaluator(expressions);
        return evaluator.evaluate();
    }
}
