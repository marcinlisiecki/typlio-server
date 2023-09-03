package com.example.typlioserver.auth.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class UserNotAuthenticatedException extends RuntimeException {

    public UserNotAuthenticatedException() {
        super(ErrorMessages.USER_NOT_AUTHENTICATED);
    }
}
