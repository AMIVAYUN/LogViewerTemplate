package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

import com.jcraft.jsch.JSchException;

public class ExecConnectionException extends RuntimeException {
    public ExecConnectionException() {
    }

    public ExecConnectionException(String message) {
        super(message);
    }

    public ExecConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecConnectionException(Throwable cause) {
        super(cause);
    }

    public ExecConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
