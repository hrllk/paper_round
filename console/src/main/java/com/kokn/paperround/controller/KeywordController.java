package com.kokn.paperround.controller;

import com.kokn.paperround.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class KeywordController {

    private final KeywordService keywordService;
    @GetMapping("/user/{userId}/keywords")
    public ResponseEntity getKeywords(@PathVariable("userId") Long userId) {

        List<String> keywords = keywordService.getKeywordListByUserId(userId);

        if (keywords.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(keywords);
    }
}
