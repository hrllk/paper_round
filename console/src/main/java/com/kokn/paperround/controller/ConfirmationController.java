package com.kokn.paperround.controller;

import com.kokn.paperround.service.ConfirmationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth/confirmation")
public class ConfirmationController {

    private final ConfirmationService confirmationService;

    @GetMapping
    public String confirmation(@RequestParam(value = "id") Long confirmationId){

        /***
         * confirmation mail
         */
        int result = confirmationService.confirmation(confirmationId);
        // TODO: result == 1 ? successTemplate : failedTemplate 그냥.. 문자열 형태로 리턴해서 뿌리자
        switch (result) {
            case 2:
                return "tokenExpired";
            default:
                return "success";
        }
    }
}
