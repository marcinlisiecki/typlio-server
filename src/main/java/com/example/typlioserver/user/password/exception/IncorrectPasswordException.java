package com.example.typlioserver.user.password.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super(ErrorMessages.INCORRECT_PASSWORD);
    }
}
