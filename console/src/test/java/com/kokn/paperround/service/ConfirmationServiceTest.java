package com.kokn.paperround.service;

import com.kokn.paperround.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")
class ConfirmationServiceTest {

    @Autowired
    ConfirmationService confirmationService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void confirmation_test() {
        Long confirmationId = 3L;
        confirmationService.confirmation(confirmationId);
    }

    @Test
    void authentication_test() throws IOException {
        int id = 1002;
        String authUrl = "http://localhost:8100/auth/confirmation?id=" + id;

        String response = HttpUtils.get(authUrl, null);
        System.out.println("response: " + response);

    }
}