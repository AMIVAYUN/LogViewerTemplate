package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

public class SshConnectionException extends RuntimeException{
    public SshConnectionException() {
    }

    public SshConnectionException(String message) {
        super( message );
    }

    public SshConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SshConnectionException(Throwable cause) {
        super(cause);
    }

    public SshConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
