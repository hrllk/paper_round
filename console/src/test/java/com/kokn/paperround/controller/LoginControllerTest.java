package com.kokn.paperround.controller;

import com.kokn.paperround.auth.LoginService;
import com.kokn.paperround.config.SecurityConfigure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = LoginController.class)
@MockBeans({
        @MockBean(LoginService.class),
        @MockBean(SecurityConfigure.class)
})
/***
 * @SpringBootTest 애노테이션은 스프링부트에서 관리하는 모든 빈들을 생성한 후,
 * 테스트를 실행하기 때문에 테스트에 많은 시간이 소요된다.
 * 단위 테스트에서는 적절치 않으므로 @WebMvcTest 애노테이션처럼 웹과 관련된 빈들만
 * 생성해주어 비교적 가볍다.
 */
class LoginControllerTest {

    /***
     * MockMvc 클래스를 통해 스프링 MVC의 동작을 재현할 수 있다.
     */
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void signup() {
//        mvc.perform(get("/signup"))
//                .andExpect(status().isOk());
//        mvc.perform(get("/signup"))
//                .andExpect(status().isOk());
    }
}