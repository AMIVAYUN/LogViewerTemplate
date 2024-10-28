package com.temp.be.controller.restcontroller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class TestRestController {

    @GetMapping("/test")
    public void test(){
        log.info("hi i'm client");
    }
}
