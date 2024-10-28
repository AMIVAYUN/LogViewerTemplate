package com.helper.controlserver.Common.Handler;


import com.helper.controlserver.Domain.PipeLine.Exception.Ssh.*;
import com.jcraft.jsch.SftpException;
import com.ssafyhelper.controlserver.Domain.PipeLine.Exception.Monitor.*;
import com.helper.controlserver.Domain.PipeLine.Dto.ResponseDto;
import com.helper.controlserver.Domain.PipeLine.Exception.Monitor.AutomatingSequenceTransferException;
import com.helper.controlserver.Domain.PipeLine.Exception.Monitor.JobNotFoundException;
import com.helper.controlserver.Domain.PipeLine.Exception.Monitor.MergingSequenceNotFoundException;
import com.ssafyhelper.controlserver.Domain.PipeLine.Exception.Ssh.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( RuntimeException.class )
    public ResponseEntity<ResponseDto> ReturnDefaultRuntimeError(){
        return ResponseEntity.internalServerError().body(ResponseDto.builder().message("서버 로직 에러").build());
    }

    @ExceptionHandler( SshConnectionException.class )
    public ResponseEntity<ResponseDto> ReturnDefaultSshException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message("ssh 커넥션 간 문제 발생")).build());
    }

    @ExceptionHandler( NullSessionException.class)
    public ResponseEntity<ResponseDto> ReturnNullSessionException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message("ssh 커넥션 세션 생성 불가")).build());
    }

    @ExceptionHandler( SshRuntimeException.class)
    public ResponseEntity<ResponseDto> ReturnSshRuntimeException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message("ssh 커넥션 사용 간 오류")).build());
    }

    @ExceptionHandler( SftpConnectionException.class)
    public ResponseEntity<ResponseDto> ReturnSftpConnectionException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message("sftp 커넥션 생성 오류")).build());
    }

    @ExceptionHandler( SftpRuntimeException.class )
    public ResponseEntity<ResponseDto> ReturnSftpRuntimeException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message("sftp 채널 사용 간 오류")).build());
    }

    @ExceptionHandler(SftpException.class)
    public ResponseEntity<ResponseDto> ReturnSftpException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message("sftp 파일 전송 간 오류 ")).build());
    }


    @ExceptionHandler(ExecConnectionException.class)
    public ResponseEntity<ResponseDto> ReturnExecConnectionException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message("Exec 채널 생성 간 오류").build()));
    }

    @ExceptionHandler( UnknowExecException.class )
    public ResponseEntity<ResponseDto> ReturnUnknownExecException(){
        return ResponseEntity.internalServerError().body(ResponseDto.builder().message("Exec 채널 사용 간 오류").build());
    }


    //------------------------------아래는 Monitor -----------------------------------

    @ExceptionHandler(AutomatingSequenceTransferException.class)
    public ResponseEntity<ResponseDto> ReturnAutomatingSequenceTransferException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message(" 파이프 라인 실행 간 오류 ")).build());
    }
    @ExceptionHandler(MergingSequenceNotFoundException.class)
    public ResponseEntity<ResponseDto> ReturnMergingSequenceNotFoundException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message(" 실행 가능 파이프라인 없음 ")).build());
    }
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ResponseDto> ReturnJobNotFoundException(){
        return ResponseEntity.internalServerError().body((ResponseDto.builder().message(" 준비 상태 파이프 라인이 없습니다. ")).build());
    }


}
