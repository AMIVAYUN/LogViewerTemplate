package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

import com.jcraft.jsch.JSchException;

public class UnknowExecException extends RuntimeException {
    public UnknowExecException() {
    }

    public UnknowExecException(String message) {
        super(message);
    }

    public UnknowExecException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknowExecException(Throwable cause) {
        super(cause);
    }

    public UnknowExecException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
