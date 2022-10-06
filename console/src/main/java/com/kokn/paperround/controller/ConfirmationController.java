package com.kokn.paperround.controller;

import com.kokn.paperround.service.ConfirmationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/confirmation")
public class ConfirmationController {

    private final ConfirmationService confirmationService;

    /***
     * TODO: success template 리턴하기(welcome template)
     */
    @GetMapping
    public ResponseEntity confirmation(@RequestParam(value = "id") Long confirmationId){

        /***
         * confirmation mail
         */
        confirmationService.confirmation(confirmationId);
        return ResponseEntity.ok().build();
    }
}
