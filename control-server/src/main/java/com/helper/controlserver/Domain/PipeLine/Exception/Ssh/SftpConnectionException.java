package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

import com.jcraft.jsch.JSchException;

public class SftpConnectionException extends RuntimeException {
    public SftpConnectionException() {
    }

    public SftpConnectionException(String message) {
        super(message);
    }

    public SftpConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SftpConnectionException(Throwable cause) {
        super(cause);
    }

    public SftpConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
