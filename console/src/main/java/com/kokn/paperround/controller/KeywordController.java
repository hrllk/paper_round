package com.kokn.paperround.controller;

import com.kokn.paperround.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class KeywordController {

    private final KeywordService keywordService;
    @PostMapping("/user/{userId}/keywords")
    public ResponseEntity getKeywords(@PathVariable("userId") Long userId) {

        keywordService.getKeywordByUserId(userId);
        return null;
    }
}
