package com.helper.controlserver.Domain.PipeLine.Controller;


import com.helper.controlserver.Domain.PipeLine.Dto.JobDto;
import com.helper.controlserver.Domain.PipeLine.Dto.ResponseDto;
import com.helper.controlserver.Domain.PipeLine.Service.MonitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
public class MonitorRestController {
    private final MonitorService service;

    @GetMapping("/control/dev/{branch}")
    public ResponseEntity<ResponseDto> MergingBackend(@PathVariable String branch){
        boolean isBe = ( branch.equals("be") );
        List<JobDto> jobs = service.getJobs();
        JobDto Sequence = service.FindMergingSequence( isBe, jobs );
        service.ActivatingJob( isBe, Sequence );
        return ResponseEntity.ok( ResponseDto.builder().message( isBe ? "백엔드 ":"프론트엔드 " + "파이프 라인 실행").build());

    }

//    @GetMapping("/control/dev/be")
//    public ResponseEntity<ResponseDto> MergingBackend(){
//        List<JobDto> jobs = service.getJobs();
//        JobDto beSequnce = service.FindMergingSequence(true, jobs );
//        service.ActivatingJob( true, beSequnce );
//        return ResponseEntity.ok( ResponseDto.builder().message("백엔드 파이프 라인 실행").build());
//
//    }
//
//
//    @GetMapping("/control/dev/fe")
//    public ResponseEntity<ResponseDto> MergingFrontend(){
//        List<JobDto> jobs = service.getJobs();
//        JobDto feSequnce = service.FindMergingSequence( false, jobs );
//        service.ActivatingJob( false, feSequnce );
//        return ResponseEntity.ok( ResponseDto.builder().message("프론트 엔드 파이프 라인 실행").build());
//    }
}
