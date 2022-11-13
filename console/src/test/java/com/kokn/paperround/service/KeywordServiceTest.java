package com.kokn.paperround.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sun.jvm.hotspot.utilities.Assert;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")
class KeywordServiceTest {


    @Autowired
    KeywordService keywordService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isOfficialWord_test_it_should_be_false() throws IOException {
        String keyword = "똻두";

        keywordService.verifyOfficialWord(keyword);

//        log.debug("result : ", result);
//        assertEquals(false, result);

    }
}