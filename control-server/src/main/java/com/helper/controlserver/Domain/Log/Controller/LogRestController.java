package com.helper.controlserver.Domain.Log.Controller;


import com.helper.controlserver.Domain.Log.Domain.LogType;
import com.helper.controlserver.Domain.Log.Dto.LogDto;
import com.helper.controlserver.Domain.Log.Service.LogSequenceService;
import com.helper.controlserver.Domain.Log.Service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class LogRestController {

    private final LogService logService;
    private final LogSequenceService logSequenceService;
//    옛날 로직
//    @GetMapping("/logs/{type}")
//    public List<LogDto> findAll(@PathVariable String type){
//
//        List<LogDto> dtoList = logService.findAll().stream().map( LogDto::from ).collect(Collectors.toList());
//        return dtoList;
//    }

    @GetMapping("/logs/{type}")
    public ResponseEntity< List<LogDto> > findAll(@PathVariable String type, @RequestParam Long id, @RequestParam int size ){
        List<LogDto> dtoList = logService.findAllWithIdAndSlice(id, size).stream().map( LogDto::from ).collect(Collectors.toList());
        return ResponseEntity.ok( dtoList );
    }

    @GetMapping("/logs/sequence/latest")
    public ResponseEntity< Long > findLatest(){
        return ResponseEntity.ok( logSequenceService.findeSequence(LogType.dev).getValue());
    }

    // 테스트 로직
//    @GetMapping("/logs/test")
//    public void test(){
//        System.out.println("hi");
//        try {
//            logService.test();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @GetMapping("/logs/new")
//    public List<Log> testNewPagingQuery(){
//        List<Log> logs = logService.findAllWithPaging( 10, 0 );
//        return logs;
//    }
//
//    @GetMapping("/logs/new2")
//    public List<Log> testNewPagingQuery2(){
//        List<Log> logs = logService.findAllWithIdAndSlice( 30000L, 10 );
//        return logs;
//    }
}
