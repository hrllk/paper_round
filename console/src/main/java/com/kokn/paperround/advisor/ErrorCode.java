package com.kokn.paperround.advisor;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    CONFLICT(HttpStatus.CONFLICT, "already exist"),
    UNOFFICIAL_WORD(HttpStatus.BAD_REQUEST, "it is not official word please check again");

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private final HttpStatus status;
    private final String message;

}
