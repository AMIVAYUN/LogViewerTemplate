package com.helper.controlserver.Domain.PipeLine.Exception.Monitor;

public class AutomatingSequenceTransferException extends RuntimeException{
    public AutomatingSequenceTransferException(String meessage) {
        super( meessage + " 파이프 라인 api 호출 간 에러 발생");
    }
}
