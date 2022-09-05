package com.kokn.paperround.console.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class HelloServiceTest {

    Logger logger = LoggerFactory.getLogger(HelloServiceTest.class);
    @Autowired
    private HelloService helloService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hello_test() {
        logger.debug("hi");
//        log.debug("hi");
        helloService.hello();
    }
}