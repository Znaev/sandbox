package com.testpay.sandbox.storage;

import com.testpay.sandbox.entity.Payment;
import com.testpay.sandbox.entity.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class PaymentStorage {

    private Map<String, Payment> paymentMap = new HashMap<>();

    public void updateState(String id, String state) {
        paymentMap.get(id).getTestPayPayment().setState(State.valueOf(state));
    }

    public Map<String, Payment> getPaymentMap() {
        return paymentMap;
    }

    public void setPaymentMap(Map<String, Payment> paymentMap) {
        this.paymentMap = paymentMap;
    }
}
