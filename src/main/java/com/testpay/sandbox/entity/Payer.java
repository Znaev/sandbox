package com.testpay.sandbox.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Payer {
    @NotBlank(message = "Customer required")
    @Email(message = "Customer not email")
    private String email;

    public Payer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
