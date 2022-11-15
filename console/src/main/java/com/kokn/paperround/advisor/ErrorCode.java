package com.kokn.paperround.advisor;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request"),
    UNOFFICIAL_WORD(HttpStatus.BAD_REQUEST, "it is not official word please check again"),
    UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "unauthorized token"),
    CONFLICT(HttpStatus.CONFLICT, "already exist");
    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private final HttpStatus status;
    private final String message;

}
