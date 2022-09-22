package com.kokn.paperround.advisor;

/***
 * 403 Exception
 */
public class ConflictException extends CustomException {
    public static int status = 409;

    public ConflictException(String message) {
        super(message, status);
    }
}

