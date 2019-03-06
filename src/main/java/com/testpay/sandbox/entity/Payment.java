package com.testpay.sandbox.entity;

public class Payment {
    private PaymentTestPay testPayPayment;
    private PaymentSandbox sandboxPayment;

    public Payment(PaymentTestPay testPayPayment, PaymentSandbox sandboxPayment) {
        this.testPayPayment = testPayPayment;
        this.sandboxPayment = sandboxPayment;
    }

    public PaymentTestPay getTestPayPayment() {
        return testPayPayment;
    }

    public void setTestPayPayment(PaymentTestPay testPayPayment) {
        this.testPayPayment = testPayPayment;
    }

    public PaymentSandbox getSandboxPayment() {
        return sandboxPayment;
    }

    public void setSandboxPayment(PaymentSandbox sandboxPayment) {
        this.sandboxPayment = sandboxPayment;
    }
}
