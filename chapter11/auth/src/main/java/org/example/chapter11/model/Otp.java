package org.example.chapter11.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Otp {

    @Id
    private String username;

    private String code;

    protected Otp() {

    }

    public Otp(String username, String code) {
        this.username = username;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
