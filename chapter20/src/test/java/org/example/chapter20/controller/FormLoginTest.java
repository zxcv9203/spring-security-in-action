package org.example.chapter20.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
public class FormLoginTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void loggingInWithWrongUser() throws Exception {
        mvc.perform(formLogin()
                        .user("unknown").password("1234"))
                .andExpect(header().exists("failed"))
                .andExpect(unauthenticated());
    }
}
