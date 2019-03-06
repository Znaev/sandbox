package com.testpay.sandbox.storage;

import com.testpay.sandbox.config.SandboxConfig;
import com.testpay.sandbox.entity.TokenResponse;
import com.testpay.sandbox.utils.RestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;


@Component
@Scope("singleton")
public class TokenStorage {
    Logger logger = LoggerFactory.getLogger(TokenStorage.class);
    @Autowired
    private SandboxConfig config;

    @Autowired
    private RestExecutor executor;

    private String token = null;

    public String getToken() {
        if (token == null) {
            accessTokenResponse();
        }
        return token;
    }

    private void accessTokenResponse() {
        TokenResponse tokenResponse = executor.auth(config.getClient(), config.getSecret());
        token = tokenResponse.getToken();
        logger.info("Token: "+token);
        int expires = tokenResponse.getExpiresIn();
        new Timer().schedule(new TokenTimerTask(this), expires * 1000);
    }

    class TokenTimerTask extends TimerTask {
        private TokenStorage tokenStorage;

        TokenTimerTask(TokenStorage tokenStorage) {
            this.tokenStorage = tokenStorage;
        }

        @Override
        public void run() {
            tokenStorage.token = null;
            logger.info("Token expired");
        }
    }
}
