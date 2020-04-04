package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;
import com.hrishikeshmishra.compiler.tokens.Token;
import com.hrishikeshmishra.compiler.tokens.Tokenizer;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InvalidTokenException {
        Tokenizer tokenizer = new Tokenizer();

        List<Token> parse = tokenizer.parse("1+  2 + 3 * 3 + ( 2 * 4) ");

        System.out.println(parse);
    }

}
