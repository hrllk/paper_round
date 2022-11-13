package com.kokn.paperround.advisor;

import com.kokn.paperround.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvisor {


    /***
     * CustomException Handler
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandle(CustomException e){
        log.error("exceptionHandler throw CustomException :[{}]", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }



}
