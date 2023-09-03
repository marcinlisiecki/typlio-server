package com.example.typlioserver.auth.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super(ErrorMessages.USERNAME_ALREADY_EXISTS);
    }
}
