package com.testpay.sandbox.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    private String scope;
    @JsonProperty("Access-Token")
    private String token;
    @JsonProperty("token_type")
    private String type;
    @JsonProperty("expires_in")
    private int expiresIn;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
