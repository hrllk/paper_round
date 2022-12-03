package com.kokn.paperround.schedule;


import com.kokn.paperround.service.BringNewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class Scheduler {


    private final BringNewsService bringNewsService;

    @Scheduled(fixedDelay = 5_000)
    public void bringNews() {
        System.out.println("hi");
        bringNewsService.bringNews();
    }
}
