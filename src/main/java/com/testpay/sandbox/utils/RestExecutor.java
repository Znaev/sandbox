package com.testpay.sandbox.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testpay.sandbox.entity.PaymentSandbox;
import com.testpay.sandbox.entity.PaymentTestPay;
import com.testpay.sandbox.entity.TokenResponse;
import com.testpay.sandbox.storage.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Locale;

@Component
public class RestExecutor {
    @Autowired
    private TokenStorage tokenStorage;

    public PaymentTestPay sendPayments(PaymentSandbox payment) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(tokenStorage.getToken());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(new ObjectMapper().writeValueAsString(payment), httpHeaders);
        ResponseEntity<PaymentTestPay> responseEntity = restTemplate.postForEntity("http://localhost:8080/payment/payment",
                entity,
                PaymentTestPay.class);
        return responseEntity.getBody();
    }

    public TokenResponse auth(String user, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(user, password);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setAcceptLanguage(Locale.LanguageRange.parse("en-US"));
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange("http://localhost:8080/oauth2/token",
                HttpMethod.POST,
                entity,
                TokenResponse.class);
        return responseEntity.getBody();
    }
}
