package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArithmeticExpressionProcessorTest {

    private ArithmeticExpressionProcessor expressionProcessor;

    @Before
    public void setup() {
        expressionProcessor = new ArithmeticExpressionProcessor();
    }

    @Test
    public void testSuccess1() throws InvalidExpressionException, InvalidTokenException {
        String str = "1+2*((10+2) * 3 * (3+3) ) ";
        expressionProcessor = new ArithmeticExpressionProcessor();
        int output = expressionProcessor.process(str);
        Assert.assertEquals(433, output);
    }

    @Test(expected = InvalidTokenException.class)
    public void testInvalidToken1() throws InvalidExpressionException, InvalidTokenException {
        String str = "1+2e";
        expressionProcessor.process(str);
    }

    @Test(expected = InvalidTokenException.class)
    public void testInvalidToken2() throws InvalidExpressionException, InvalidTokenException {
        String str = "1+2+(3+3";
        expressionProcessor.process(str);
    }
}
