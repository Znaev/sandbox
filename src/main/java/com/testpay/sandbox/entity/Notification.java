package com.testpay.sandbox.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
    private String currency;
    private String amount;
    private String id;
    @JsonProperty("external_id")
    private String externalId;
    private String status;
    private String sha2sig;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSha2sig() {
        return sha2sig;
    }

    public void setSha2sig(String sha2sig) {
        this.sha2sig = sha2sig;
    }
}
