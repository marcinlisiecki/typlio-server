package com.example.typlioserver.user.password.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class PasswordResetTokenNotFoundException extends RuntimeException {

    public PasswordResetTokenNotFoundException() {
        super(ErrorMessages.PASSWORD_RESET_TOKEN_NOT_FOUND);
    }
}
