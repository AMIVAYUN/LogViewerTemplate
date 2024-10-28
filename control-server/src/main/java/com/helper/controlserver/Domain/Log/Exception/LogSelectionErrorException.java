package com.helper.controlserver.Domain.Log.Exception;

public class LogSelectionErrorException extends RuntimeException{
    public LogSelectionErrorException(String meessage) {
        super( meessage + " 파이프 라인 api 호출 간 에러 발생");
    }
}
