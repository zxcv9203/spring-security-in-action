package org.example.business.model;

public class Otp {

    private final String username;

    private final String code;

    public Otp(String username, String code) {
        this.username = username;
        this.code = code;
    }
}
