package com.kokn.paperround.controller;

import com.kokn.paperround.auth.LoginService;
import com.kokn.paperround.dto.SignInDto;
import com.kokn.paperround.dto.SignInResponseDto;
import com.kokn.paperround.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity signup(@ModelAttribute SignUpDto dto) {
        loginService.signup(dto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@ModelAttribute SignInDto dto) {

        SignInResponseDto response = loginService.signIn2(dto);
        return ResponseEntity.ok(response);
    }
}
