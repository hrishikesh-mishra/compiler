package com.hrishikeshmishra.compiler;

import com.hrishikeshmishra.compiler.exceptions.InvalidExpressionException;
import com.hrishikeshmishra.compiler.exceptions.InvalidTokenException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.accessibility.AccessibleAction;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticExpressionProcessorTest {

    private ArithmeticExpressionProcessor expressionProcessor;
    private List<String> lines = new ArrayList<>();

    @Before
    public void setup() {
        expressionProcessor = new ArithmeticExpressionProcessor();
    }

    @After
    public void cleanup() {
        lines.clear();
    }

    @Test
    public void testSuccess1() throws InvalidExpressionException, InvalidTokenException {
        lines.add("a = 1+2*((10+2) * 3 * (3+3) ) ");
        expressionProcessor = new ArithmeticExpressionProcessor();
        int output = expressionProcessor.process(lines);

        Assert.assertEquals(433, output);
    }

    @Test(expected = InvalidTokenException.class)
    public void testInvalidToken1() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b=1+2e");
        try {
            expressionProcessor.process(lines);
        } catch (Exception e) {
            Assert.assertEquals("Variable can't be start with digit. line: 0, column: 5", e.getMessage());
            throw e;
        }
    }

    @Test(expected = InvalidTokenException.class)
    public void testInvalidToken2() throws InvalidExpressionException, InvalidTokenException {
        lines.add("A=1+2+(3+3");
        try {
            expressionProcessor.process(lines);
        } catch (Exception e) {
            Assert.assertEquals("Invalid Token Expression at position: 10 and expected token was: )", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testNegativeNumber1() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b=-1");
        int output = expressionProcessor.process(lines);
        Assert.assertEquals(-1, output);
    }

    @Test
    public void testNegativeNumber2() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b=-1-5");
        int output = expressionProcessor.process(lines);
        Assert.assertEquals(-6, output);
    }

    @Test
    public void testExpression3() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b=-( -10 -(20))");
        int output = expressionProcessor.process(lines);
        Assert.assertEquals(30, output);
    }

    @Test
    public void testExpression4() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b = 1 + ( -1 -(1+1))");
        int output = expressionProcessor.process(lines);
        Assert.assertEquals(-2, output);
    }

    @Test(expected = InvalidTokenException.class)
    public void testWithoutLValue() throws InvalidExpressionException, InvalidTokenException {
        lines.add("2 * 3  + 7");
        try {
            expressionProcessor.process(lines);
        } catch (Exception e) {
            Assert.assertEquals("Invalid Token Expression at position: 0 and expected token was: variable", e.getMessage());
            throw e;
        }
    }

    @Test(expected = InvalidTokenException.class)
    public void testWithoutAssignment() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b 2 * 3");
        try {
            expressionProcessor.process(lines);
        } catch (Exception e) {
            Assert.assertEquals("Invalid Token Expression at position: 1 and expected token was: =", e.getMessage());
            throw e;
        }
    }

    @Test(expected = InvalidExpressionException.class)
    public void testVariableNotDefined() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b = a + 1");
        try {
            expressionProcessor.process(lines);
        } catch (Exception e){
            Assert.assertEquals("a - not defined or assigned", e.getMessage());
            throw e;
        }

    }

    @Test
    public void testVariableWithDigits() throws InvalidExpressionException, InvalidTokenException {
        lines.add("b3=10*2");
        lines.add("c=b3+b3*b3");
        int result = expressionProcessor.process(lines);
        Assert.assertEquals(420, result);
    }


}
