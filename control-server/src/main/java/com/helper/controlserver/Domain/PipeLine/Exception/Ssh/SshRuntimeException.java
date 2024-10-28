package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

public class SshRuntimeException extends RuntimeException {
    public SshRuntimeException(String message) {
        super(message);
    }

    public SshRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SshRuntimeException(Throwable cause) {
        super(cause);
    }

    public SshRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SshRuntimeException() {
    }
}
