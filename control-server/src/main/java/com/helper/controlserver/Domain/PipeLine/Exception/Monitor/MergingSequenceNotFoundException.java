package com.helper.controlserver.Domain.PipeLine.Exception.Monitor;

public class MergingSequenceNotFoundException extends RuntimeException {
    public MergingSequenceNotFoundException(){
        super( "사용 가능한 가용 파이프 라인이 없습니다. ");
    }
}
