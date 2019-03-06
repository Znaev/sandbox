package com.testpay.sandbox.controller;

import com.testpay.sandbox.storage.PaymentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SandboxController {
    @Autowired
    private PaymentStorage paymentStorage;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getPaymentPage() {
        return "home.html";
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getStatusPage() {
        return "status.html";
    }
}
