package org.example.chapter10.repository;

import org.example.chapter10.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByIdentifier(String identifier);
}
