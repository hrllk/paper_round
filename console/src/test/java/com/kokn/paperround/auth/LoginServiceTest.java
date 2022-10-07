package com.kokn.paperround.auth;

import com.kokn.paperround.dto.SignInDto;
import com.kokn.paperround.dto.SignUpDto;
import com.kokn.paperround.dto.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@Slf4j
@ActiveProfiles("local")
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Commit
    void signup_test() {

        log.debug("hi");
        SignUpDto dto = new SignUpDto();
        dto.setEmail("roovcloudy@gmail.com");
        dto.setPassword("tmfvmsdkdl1!");
        loginService.signup(dto);

    }
    @Test
    @Commit
    void signin_test() {

        log.debug("hi");
        SignInDto dto = new SignInDto();
        dto.setEmail("roovcloudy@gmail.com");
        dto.setPassword("tmfvmsdkdl1!");

        TokenDto responseToken = loginService.signin(dto);
        log.debug("\n\n response(generated) Token : [{}] \n\n", responseToken);

    }
}