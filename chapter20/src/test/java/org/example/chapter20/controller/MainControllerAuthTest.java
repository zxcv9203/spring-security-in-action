package org.example.chapter20.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerAuthTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void helloAuthenticatingWithValidUser() throws Exception {
        mvc.perform(get("/")
                        .with(httpBasic("kim", "12345")))
                .andExpect(status().isOk());
    }

    @Test
    void helloAuthenticatingWithInvalidUser() throws Exception {
        mvc.perform(get("/").with(httpBasic("mary", "12345")))
                .andExpect(status().isUnauthorized());
    }
}
