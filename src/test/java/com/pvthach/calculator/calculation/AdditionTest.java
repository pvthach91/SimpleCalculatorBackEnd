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
public class AdditionTest {

    @Before
    public void setup() {
    }

    @Test
    public void testAddition() throws Exception {
        Addition addition = new Addition(2, 4);
        double actual = addition.calculate();
        assertEquals(6.0, actual, 0);
    }

    @Test
    public void testWrongAddition() throws Exception {
        Addition addition = new Addition(2, 4);
        double actual = addition.calculate();
        assertNotEquals(7.0, actual, 0);
    }

    @Test
    public void testGenerateHistory() throws Exception {
        double first = 2;
        double second = 4;
        Addition addition = new Addition(first, second);
        double result = addition.calculate();
        String expect = first + " + " + second + " = " + result;
        assertEquals(expect, addition.generateHistory(result));
    }
}
