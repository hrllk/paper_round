package com.kokn.paperround.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")

public class ConfirmationTokenTest {

    @Test
    void generate_token_test() {
        String usrId = "roovcloudy@gmail.com";
        ConfirmationToken token = ConfirmationToken.createEmailConfirmationToken(usrId);
        log.debug("token: [{}]", token);
    }
}
