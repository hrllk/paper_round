package com.kokn.paperround.advisor;


import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    private ErrorCode errorCode;

}
