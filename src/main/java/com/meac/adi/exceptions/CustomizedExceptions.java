package com.meac.adi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomizedExceptions {

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class DuplicateUserInfoException extends RuntimeException {
        public DuplicateUserInfoException(String message) {
            super(message);
        }
    }
}
