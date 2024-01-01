package org.example.chapter20.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NameServiceTest {

    @Autowired
    private NameService nameService;

    @Test
    public void noUserTest() {
        assertThatCode(() -> nameService.getName())
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    @WithMockUser(authorities = "read")
    public void wrongAuthorityUserTest() {
        assertThatCode(() -> nameService.getName())
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(authorities = "write")
    void successTest() {
        String name = nameService.getName();

        assertThat(name).isEqualTo("writeUser");
    }
}