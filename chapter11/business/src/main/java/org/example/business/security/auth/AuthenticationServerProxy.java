package org.example.business.security.auth;

import org.example.business.model.Otp;
import org.example.business.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {

    private final RestTemplate restTemplate;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public AuthenticationServerProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";

        User user = new User(username, password);

        HttpEntity<User> request = new HttpEntity<>(user);

        restTemplate.postForEntity(url, request, Void.class);
    }

    public boolean sendOTP(String username, String code) {
        String url = "baseUrl" + "/otp/check";

        Otp otp = new Otp(username, code);

        HttpEntity<Otp> request = new HttpEntity<>(otp);

        return HttpStatus.OK.equals(
                restTemplate.postForEntity(url, request, Void.class)
                        .getStatusCode()
        );
    }
}
