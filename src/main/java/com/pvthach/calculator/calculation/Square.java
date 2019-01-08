package com.pvthach.calculator.calculation;

import lombok.NoArgsConstructor;

/**
 * Created by THACH-PC
 */

@NoArgsConstructor
public class Square extends OneParamCalculation {

    public Square(double firstParam) {
        super(firstParam);
    }

    @Override
    public String generateHistory(double result) {
        return (this.param + " square " + "= " + result);
    }

    @Override
    public double calculate() {
        return this.param * this.param;
    }
}
