package com.dali.dali.global.exception;

public class ParticipationAlreadyConfirmException extends RuntimeException {
    public ParticipationAlreadyConfirmException() {
        super();
    }

    public ParticipationAlreadyConfirmException(String message) {
        super(message);
    }

    public ParticipationAlreadyConfirmException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParticipationAlreadyConfirmException(Throwable cause) {
        super(cause);
    }
}
