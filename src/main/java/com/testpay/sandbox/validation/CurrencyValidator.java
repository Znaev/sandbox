package com.testpay.sandbox.validation;

import com.testpay.sandbox.validation.anotation.IsCurrency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

public class CurrencyValidator implements ConstraintValidator<IsCurrency, String> {

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Currency.getInstance(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
