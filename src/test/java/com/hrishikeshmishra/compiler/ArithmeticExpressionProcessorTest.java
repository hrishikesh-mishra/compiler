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

    @Test
    public void testNegativeNumber1() throws InvalidExpressionException, InvalidTokenException {
        String str = "-1";
        int output = expressionProcessor.process(str);
        Assert.assertEquals(-1, output);
    }

    @Test
    public void testNegativeNumber2() throws InvalidExpressionException, InvalidTokenException {
        String str = "-1-5";
        int output = expressionProcessor.process(str);
        Assert.assertEquals(-6, output);
    }

    @Test
    public void testExpression3() throws InvalidExpressionException, InvalidTokenException {
        String str = "-( -10 -(20))";
        int output = expressionProcessor.process(str);
        Assert.assertEquals(30, output);
    }

    @Test
    public void testExpression4() throws InvalidExpressionException, InvalidTokenException {
        String str = "1 + ( -1 -(1+1))";
        int output = expressionProcessor.process(str);
        Assert.assertEquals(-2, output);
    }

//

}
