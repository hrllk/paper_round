package com.kokn.paperround.controller;

import com.kokn.paperround.auth.LoginService;
import com.kokn.paperround.dto.UserKeywordRequest;
import com.kokn.paperround.entity.Keyword;
import com.kokn.paperround.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class KeywordController {

    private final KeywordService keywordService;
    private final LoginService loginService;
    @GetMapping("/users/{userId}/keywords")
    public ResponseEntity getUsersKeywordList(@RequestHeader HttpHeaders headers, @PathVariable("userId") Long userId) {
//        public ResponseEntity getKeywords(@RequestHeader HttpHeaders headers, @RequestBody Long userId) {

        loginService.verify(headers, userId);

        List<String> keywords = keywordService.getKeywordListByUserId(userId);

        return ResponseEntity.ok(keywords);
    }

    @PostMapping("/users/keywords")
    public ResponseEntity<?> register(@RequestHeader HttpHeaders headers, @RequestBody UserKeywordRequest dto) {

        loginService.verify(headers, dto.getUserId());

        keywordService.register(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/keywords")
    public ResponseEntity<?> removeKeyword(@RequestHeader HttpHeaders headers, @RequestBody UserKeywordRequest dto) {

        loginService.verify(headers, dto.getUserId());

        keywordService.remove(dto);

        return ResponseEntity.ok().build();
    }






    @GetMapping("/keywords")
    public ResponseEntity getKeywordList() {


        List<Keyword> keywordList = keywordService.getKeywordList();
        log.debug("keywordList: [{}]", keywordList);
        List<com.kokn.paperround.dto.Keyword> data = com.kokn.paperround.dto.Keyword.fromEntity(keywordList);

        return ResponseEntity.ok(data);
    }

}
