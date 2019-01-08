package com.pvthach.calculator.calculation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

/**
 * Created by THACH-PC
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Addition.class, name = "addition"),
        @JsonSubTypes.Type(value = Subtraction.class, name = "subtraction"),
        @JsonSubTypes.Type(value = Division.class, name = "division"),
        @JsonSubTypes.Type(value = Multiplication.class, name = "multiplication")
})
@NoArgsConstructor
public abstract class TwoParamCalculation extends Calculator {

    protected double firstParam;

    protected double secondParam;

    protected TwoParamCalculation(double firstParam, double secondParam) {
        this.firstParam = firstParam;
        this.secondParam = secondParam;
    }

    public double getFirstParam() {
        return firstParam;
    }

    public double getSecondParam() {
        return secondParam;
    }
}
