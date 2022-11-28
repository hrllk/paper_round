package com.kokn.paperround.advisor;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request"),
    UNOFFICIAL_WORD(HttpStatus.BAD_REQUEST, "it is not official word please check again"),
    USER_NOT_CONFIRM(HttpStatus.FORBIDDEN, "this user does not confirm yet"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user not found"),
    UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "unauthorized token"),
    CONFLICT(HttpStatus.CONFLICT, "already exist");
    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private final HttpStatus status;
    private final String message;

}
