package com.pvthach.calculator.calculation;

import com.pvthach.calculator.SimpleCalculatorBackEndApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by THACH-PC
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SimpleCalculatorBackEndApplication.class)
@SpringBootTest
public class MultiplicationTest {

    @Before
    public void setup() {
    }

    @Test
    public void testMultiplication() throws Exception {
        Multiplication multiplication = new Multiplication(2, 4);
        double actual = multiplication.calculate();
        assertEquals(8.0, actual, 0);
    }

    @Test
    public void testWrongMultiplication() throws Exception {
        Multiplication multiplication = new Multiplication(2, 4);
        double actual = multiplication.calculate();
        assertNotEquals(7.0, actual, 0);
    }

    @Test
    public void testGenerateHistory() throws Exception {
        double first = 12;
        double second = 4;
        Multiplication multiplication = new Multiplication(first, second);
        double result = multiplication.calculate();
        String expect = first + " * " + second + " = " + result;
        assertEquals(expect, multiplication.generateHistory(result));
    }
}
