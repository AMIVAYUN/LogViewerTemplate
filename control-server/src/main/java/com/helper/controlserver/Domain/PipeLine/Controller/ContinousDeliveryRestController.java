package com.helper.controlserver.Domain.PipeLine.Controller;


import com.helper.controlserver.Domain.PipeLine.Dto.ArtifactRequestDto;
import com.helper.controlserver.Domain.PipeLine.Dto.ResponseDto;
import com.helper.controlserver.Domain.PipeLine.Service.SshConnectionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ContinousDeliveryRestController {
    @Value("${secret.ssh.privateKeyPath}")
    private String pkeyPath;


    @Value("${secret.ssh.host}")
    private String host;

    @Value("${secret.ssh.username}")
    private String username;

    @Value("${secret.ssh.port}")
    private Integer port;

    @Value("${secret.besource}")
    private String beSource;

    @Value( "${secret.fesource}")
    private String feSource;

    @Value( "${secret.arrival}")
    private String arrival;

    private final SshConnectionService service;

    @PostConstruct
    public void PathCheck(){
        log.info("pwd: " + System.getProperty("user.home"));
    }



    @PostMapping("/control/dev/deploy")
    public ResponseEntity<ResponseDto> sendDeployArtifacts(@RequestBody ArtifactRequestDto dto ) {



        // TransPort Layer
        service.sendArtifact( pkeyPath, username, host, port, dto.isBe ? beSource : feSource, arrival );

        // Shell Command Layer
        service.execCommand( pkeyPath, username, host, port, dto.isBe );

        return ResponseEntity.ok().body(ResponseDto.builder().message("배포 서버 아티팩트 전송 성공").build() );
    }

//    @GetMapping("/test")
    public String temp(){



        return "hi";
    }


}
