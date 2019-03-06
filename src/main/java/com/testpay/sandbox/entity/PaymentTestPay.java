package com.testpay.sandbox.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentTestPay {
    private String id;
    @JsonProperty("create_time")
    private String createTime;
    private State state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
