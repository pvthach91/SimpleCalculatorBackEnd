package com.pvthach.calculator.calculation;

/**
 * Created by THACH-PC
 * Class not use now, it's for future use in case we have another calculator, we extend Calculator like this
 * (just how that we can easily extend the calculator in the future)
 */

public class FutureCalculation extends Calculator {

    protected FutureCalculation() {
    }

    @Override
    public double calculate() {
        // Implement the way to calculate here

        return 0;
    }

    @Override
    public String generateHistory(double result) {
        // Implement the way to generate history here

        return null;
    }
}
