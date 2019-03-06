package com.testpay.sandbox.entity;

import com.testpay.sandbox.validation.anotation.IsAmount;
import com.testpay.sandbox.validation.anotation.IsCurrency;

import javax.validation.constraints.NotNull;

public class Amount {
    @NotNull(message = " Amount required")
    @IsAmount(message = "Amount too long")
    private Double value;
    @IsCurrency(message = " Wrong currency")
    private String currency;

    public Amount() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
