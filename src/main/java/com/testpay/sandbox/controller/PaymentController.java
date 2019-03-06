package com.testpay.sandbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testpay.sandbox.entity.Notification;
import com.testpay.sandbox.entity.Payment;
import com.testpay.sandbox.entity.PaymentSandbox;
import com.testpay.sandbox.entity.PaymentTestPay;
import com.testpay.sandbox.storage.PaymentStorage;
import com.testpay.sandbox.utils.HashCalculator;
import com.testpay.sandbox.utils.RestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private RestExecutor executor;

    @Autowired
    private PaymentStorage paymentStorage;

    @Autowired
    private HashCalculator hashCalculator;

    private int id = 1;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity sendPayment(@Valid @RequestBody PaymentSandbox payment, HttpServletRequest request) throws JsonProcessingException, MalformedURLException {
        try {
            payment.setIntent("order");
            payment.getTransaction().setExternalId(id++);
            payment.setNotification(getURLBase(request) + "/notify");
            PaymentTestPay responseObject = executor.sendPayments(payment);
            paymentStorage.getPaymentMap().put(responseObject.getId(), new Payment(responseObject, payment));
            logger.info("Payment with id: " + responseObject.getId() + " Successful created");
            return ResponseEntity.ok(responseObject);
        } catch (HttpStatusCodeException e) {
            int code;
            if (e.getStatusCode().value() == 400) {
                code = 402;
            } else {
                code = e.getStatusCode().value();
            }
            return ResponseEntity.status(code).build();
        }
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public void notification(@RequestBody Notification notification) throws NoSuchAlgorithmException {
        if (hashCalculator.isValidNotification(notification)) {
            paymentStorage.updateState(notification.getId(), notification.getStatus());
            logger.info("Payment with id: " + notification.getId() + ". Status updated to " + notification.getStatus());
        } else {
            logger.info("Notification for " + notification.getId() + " have wrong signature");
        }
    }

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public List<Payment> getPayments() {
        return new ArrayList<>(paymentStorage.getPaymentMap().values());
    }

    private String getURLBase(HttpServletRequest request) throws MalformedURLException {
        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
    }

}
