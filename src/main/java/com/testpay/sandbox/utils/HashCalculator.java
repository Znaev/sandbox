package com.testpay.sandbox.utils;

import com.google.common.hash.Hashing;
import com.testpay.sandbox.config.SandboxConfig;
import com.testpay.sandbox.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

@Component
public class HashCalculator {
    @Autowired
    SandboxConfig sandboxConfig;

    public boolean isValidNotification(Notification notification) throws NoSuchAlgorithmException {
        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder
                .append(notification.getCurrency())
                .append(notification.getAmount())
                .append(Hashing.sha256().hashString(sandboxConfig.getSecret(), StandardCharsets.US_ASCII).toString().toUpperCase())
                .append(notification.getId())
                .append(notification.getExternalId())
                .append(notification.getStatus());
        return Hashing.sha256().hashString(inputBuilder.toString(), StandardCharsets.US_ASCII).toString().equals(notification.getSha2sig());

    }
}
