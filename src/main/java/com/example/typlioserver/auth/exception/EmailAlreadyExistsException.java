package com.example.typlioserver.auth.exception;

import com.example.typlioserver.common.ErrorMessages;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super(ErrorMessages.EMAIL_ALREADY_EXISTS);
    }
}
