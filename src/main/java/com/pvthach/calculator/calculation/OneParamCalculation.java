package com.pvthach.calculator.calculation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

/**
 * Created by THACH-PC
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Square.class, name = "square")
})
@NoArgsConstructor
public abstract class OneParamCalculation extends Calculator {

    protected double param;

    protected OneParamCalculation(double param) {
        this.param = param;
    }

    public double getParam() {
        return param;
    }
}
