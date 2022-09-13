package com.kokn.paperround.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {


    public void hello(){
//        log.debug("Hi");
        System.out.println("hi");
    }
}
