package com.example.typlioserver.common.exception;

import com.example.typlioserver.common.constants.ErrorMessages;

public class InsufficientPermissionsException extends RuntimeException {

    public InsufficientPermissionsException() {
        super(ErrorMessages.INSUFFICIENT_PERMISSIONS);
    }
}
