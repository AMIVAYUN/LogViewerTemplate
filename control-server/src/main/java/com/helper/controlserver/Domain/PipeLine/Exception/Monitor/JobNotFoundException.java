package com.helper.controlserver.Domain.PipeLine.Exception.Monitor;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(){
        super("사용 가능한 Job이 없습니다.");
    }
}
