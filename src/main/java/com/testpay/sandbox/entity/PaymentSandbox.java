package com.testpay.sandbox.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

public class PaymentSandbox {

    private String intent = "order";
    @JsonProperty("notification_url")
    private String notification;
    @Valid
    private Payer payer;
    @Valid
    private Transaction transaction;

    public PaymentSandbox() {
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
