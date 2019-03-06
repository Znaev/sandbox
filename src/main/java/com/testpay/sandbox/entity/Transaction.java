package com.testpay.sandbox.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

public class Transaction {
    @JsonProperty("external_id")
    private int externalId = 123456;
    private String description;
    @Valid
    private Amount amount;

    public Transaction() {
    }


    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
