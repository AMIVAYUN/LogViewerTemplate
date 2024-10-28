package com.helper.controlserver.Domain.Log.Controller;


import com.helper.controlserver.Domain.Log.Infra.Repository.LogSequenceMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LogViewController {

    private final LogSequenceMongoRepository repository;
    @GetMapping("/viewer")
    public String getViewer(){
        return "templates/log";
    }



}
