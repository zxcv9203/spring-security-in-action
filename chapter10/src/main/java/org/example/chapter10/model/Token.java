package org.example.chapter10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.Comment;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("클라이언트의 식별자")
    private String identifier;

    @Comment("애플리케이션이 클라이언트를 위해 생성한 CSRF 토큰")
    private String token;

    protected Token() {
    }

    public Token(String identifier, String token) {
        this.identifier = identifier;
        this.token = token;
    }

    public void changeToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getToken() {
        return token;
    }
}
