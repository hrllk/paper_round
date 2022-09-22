package com.kokn.paperround.advisor;


import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private String message;
    private int status;

    public CustomException(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
