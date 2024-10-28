package com.helper.controlserver.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.helper.controlserver.Domain.PipeLine.Exception.Ssh.SshConnectionException;
import com.helper.controlserver.Domain.PipeLine.Exception.Ssh.UnknownSftpException;
import com.helper.controlserver.Domain.PipeLine.Service.SshConnectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
class SshConnectionServiceTest {


    @Autowired
    SshConnectionService service;

    private final String pwd = System.getProperty("user.home")+ File.separator;
    private final String testKey = pwd + "DevOps.pem";
    private final String username = "ubuntu";
    private final String host = "ssafyhelper.shop";
    private final int port = 22;
    private final String source = pwd + "sendData.txt";

    private final String arrival = "/home/ubuntu/deploy";
    @Test
    void Jsch_세션_생성_성공() {
        //given, when
        //세션
        Session session = service.CreateSession(testKey,username,host,port);
        //then

        assertNotNull( session );
        assertEquals( session.getHost(), host );
        session.disconnect();
    }

    @Test
    void Jsch_세션_생성_실패() {
        //then
        assertThrows( SshConnectionException.class, () -> service.CreateSession(testKey,null,host,port) );
    }

    // 무조건 성공한다면 private 메소드 였어야 되지 않았을까?
    @Test
    void Sftp채널_생성_성공() {
        //given
        Session session = service.CreateSession(testKey, username, host, port);
        //when
        ChannelSftp channelSftp = service.CreateChannelSftp(session);
        //then
        assertNotNull(channelSftp);
    }


    @Test
    void 전송_성공() {
        //given //when
        File file = new File( source );
        System.out.println( "Arrive: " + arrival );
        try{
            if( file.exists() ){
                file.createNewFile();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(" test");
            bw.flush();
            bw.close();

            //then
            assertDoesNotThrow( () -> service.sendArtifact( testKey, username, host, port, source, arrival));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void 전송_실패() {
        //given when then
        assertThrows( UnknownSftpException.class, () ->{
            service.sendArtifact( testKey, username, host, port, pwd + System.currentTimeMillis(), arrival);
        });

    }
}