package com.pvthach.calculator.calculation;

import lombok.NoArgsConstructor;

/**
 * Created by THACH-PC
 */

@NoArgsConstructor
public class Multiplication extends TwoParamCalculation {

    public Multiplication(double firstParam, double secondParam) {
        super(firstParam, secondParam);
    }

    @Override
    public String generateHistory(double result) {
        return (this.firstParam + " * " + this.secondParam + " = " + result);
    }

    @Override
    public double calculate() {
        return this.firstParam * this.secondParam;
    }
}
