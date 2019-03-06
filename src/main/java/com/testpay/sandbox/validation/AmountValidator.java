package com.testpay.sandbox.validation;

import com.testpay.sandbox.validation.anotation.IsAmount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<IsAmount, Double> {

    @Override
    public boolean isValid(Double input, ConstraintValidatorContext constraintValidatorContext) {
        input = Math.round(input * 100.0) / 100.0;
        return String.valueOf(input).length() < 11;
    }
}
