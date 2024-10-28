package com.helper.controlserver.Domain.PipeLine.Exception.Ssh;

public class NullSessionException extends RuntimeException{
    public NullSessionException( String message ){
        super( message );
    }
}
