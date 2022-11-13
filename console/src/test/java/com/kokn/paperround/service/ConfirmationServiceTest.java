package com.kokn.paperround.service;

import com.kokn.paperround.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    void encyclopedia_test() {
        Map header = new HashMap();
//        curl "https://openapi.naver.com/v1/search/encyc.json?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim" \
//        -H "X-Naver-Client-Id: qXPada7nrmo0GeQ1aio9" \
//        -H "X-Naver-Client-Secret: zT2QlFbooc" -v
        header.put("X-Naver-Client-Id", "qXPada7nrmo0GeQ1aio9");
        header.put("X-Naver-Client-Secret", "zT2QlFbooc");
        String url = "https://openapi.naver.com/v1/search/encyc.json?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim";

        Response response = HttpUtils.getResponse(url, header);
        log.debug("response : [{}]", response);

    }

    @Test
    void is() {

    }
}