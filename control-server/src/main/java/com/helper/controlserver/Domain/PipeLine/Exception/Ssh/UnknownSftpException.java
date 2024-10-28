package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

public class UnknownSftpException extends RuntimeException{

    public UnknownSftpException() {
    }

    public UnknownSftpException(String message) {
        super(message);
    }

    public UnknownSftpException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownSftpException(Throwable cause) {
        super(cause);
    }

    public UnknownSftpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
