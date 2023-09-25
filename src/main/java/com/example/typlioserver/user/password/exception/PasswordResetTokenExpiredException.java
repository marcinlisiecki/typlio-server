package com.example.typlioserver.user.password.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class PasswordResetTokenExpiredException extends RuntimeException {

    public PasswordResetTokenExpiredException() {
        super(ErrorMessages.PASSWORD_RESET_TOKEN_EXPIRED);
    }
}
