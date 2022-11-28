package com.kokn.paperround.controller;

import com.kokn.paperround.auth.LoginService;
import com.kokn.paperround.dto.SignInRequest;
import com.kokn.paperround.dto.SignInResponseDto;
import com.kokn.paperround.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequest signUpRequest) {
        loginService.signup(signUpRequest);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signIn")
//    public ResponseEntity<?> signIn(@ModelAttribute SignInDto dto) {
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        SignInResponseDto response = loginService.signIn2(signInRequest);
        return ResponseEntity.ok(response);
    }
}
