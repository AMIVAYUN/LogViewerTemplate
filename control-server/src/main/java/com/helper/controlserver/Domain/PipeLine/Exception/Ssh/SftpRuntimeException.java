package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

public class SftpRuntimeException extends RuntimeException {
    public SftpRuntimeException() {
    }

    public SftpRuntimeException(String message) {
        super(message);
    }

    public SftpRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SftpRuntimeException(Throwable cause) {
        super(cause);
    }

    public SftpRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
