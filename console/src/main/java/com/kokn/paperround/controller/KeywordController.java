package com.kokn.paperround.controller;

import com.kokn.paperround.auth.LoginService;
import com.kokn.paperround.component.TokenProvider;
import com.kokn.paperround.dto.UserKeywordRequest;
import com.kokn.paperround.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity getKeywords(@PathVariable("userId") Long userId) {

        List<String> keywords = keywordService.getKeywordListByUserId(userId);

        return ResponseEntity.ok(keywords);
    }

    /***
     *
     * A라는 사용자가있고 해당 사용자가 정상 회원일때,
     * 그리고 그 사용자가 userId를 조작해서 다른사용자의 키워드목록을 조회해보려고한다면 이것을 어떻게 막지?
     *
     * 전송된 사용자의 토큰값을 이용해 그 토큰값과 userId가 일치하는지 검증하는 로직이 필요함..
     *
     */
    @PostMapping("/users/keywords")
    public ResponseEntity<?> register(@RequestHeader HttpHeaders headers, @RequestBody UserKeywordRequest dto) {

        loginService.verify(headers, dto.getUserId());

        keywordService.register(dto);

        return ResponseEntity.ok().build();
    }

    //    @DeleteMapping("/keywords")
    @RequestMapping(value = "/keywords", method = RequestMethod.DELETE)
//    public ResponseEntity removeKeyword() {
    public ResponseEntity<?> removeKeyword(@RequestHeader HttpHeaders headers, @RequestBody UserKeywordRequest dto) {


//        Long userId = dto.getUserId();
//        String keyword = dto.getKeyword();
        /***
         * 헤더에는 사용자의 토큰값이담겨있고,
         * Body에는 사용자의 아이디값이 담겨있다.
         * 요청이들어오면 이 두값을 비교하여 같은사용자인지 먼저 체크한 후,
         * 같은사용자일경우, 로직을 수행하고, 아니라면 에러를 던진다.
         */

        String accessToken = String.valueOf(headers.get("Authorization")).replace("[","").replace("]","");

//        headers.
        Authentication authentication = new TokenProvider().getAuthentication(accessToken);

        log.debug("hi");
        keywordService.remove(dto);
        log.debug("dfkdkf");

        return ResponseEntity.ok().build();
    }


}
