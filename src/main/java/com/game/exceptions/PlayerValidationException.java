package com.game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayerValidationException extends RuntimeException {

    public PlayerValidationException() {
        super();
    }

    public PlayerValidationException(String message) {
        super(message);
    }

    public PlayerValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerValidationException(Throwable cause) {
        super(cause);
    }

    protected PlayerValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
