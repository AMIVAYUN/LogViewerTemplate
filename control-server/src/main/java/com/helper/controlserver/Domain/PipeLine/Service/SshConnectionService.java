package com.helper.controlserver.Domain.PipeLine.Service;


import com.helper.controlserver.Domain.PipeLine.Exception.Ssh.*;
import com.jcraft.jsch.*;
import com.ssafyhelper.controlserver.Domain.PipeLine.Exception.Ssh.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
@Slf4j
@RequiredArgsConstructor
public class SshConnectionService{

    public Session CreateSession( String pkeyPath, String username, String host, int port ){

        try{
            //세션 생성
            JSch jsch = new JSch();
            jsch.addIdentity(pkeyPath);
            Session session = jsch.getSession(username,host,port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            return session;

        }catch (JSchException e ){
            log.error( e.getMessage() );
            throw new SshConnectionException( e );
        }catch( RuntimeException e ){
            log.error( e.getMessage() );
            throw new SshRuntimeException( e );
        }
    }

    public ChannelSftp CreateChannelSftp( Session session ) {
        try {
            //채널 생성
            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            return sftp;

        } catch (NullPointerException e) {
            log.error( e.getMessage() );
            throw new NullSessionException(e.getMessage());
        } catch (JSchException e) {
            log.error( e.getMessage() );
            throw new SftpConnectionException( e );
//            throw new SftpConnectionException( e.getCause().toString() + e.getMessage() );
        }catch( RuntimeException e ){
            log.error( e.getMessage() );
            throw new SftpRuntimeException( e );
        }

    }

    public void sendArtifact( String pkeyPath, String username, String host, int port, String source, String arrival )  {
        log.info( "Send Artifact: " + pkeyPath + " : " + username + " : " + host+ " : "+ port + " : " + source + " : " + arrival);
        Session session = CreateSession( pkeyPath, username, host, port );
        ChannelSftp sftp = CreateChannelSftp( session );
        //파일 전송
        try{
            log.info( source + "전송 시도");
            sftp.put( source, arrival );

            log.info( "전송 결과: ");

            sftp.disconnect();
            session.disconnect();
        }catch( SftpException e ) {
            log.error( e.getMessage() );
            throw new UnknownSftpException(e);
        }
    }


    public void execCommand(String pkeyPath, String username, String host, Integer port, boolean isBe ) {

        try{
            Session session = CreateSession( pkeyPath, username, host, port );
            ChannelExec exec = CreateChannelExec( session );
            String command = isBe ? "sh /home/ubuntu/deploy/backend.sh 2>&1 >> /home/ubuntu/deploy/backend.log":"sh /home/ubuntu/deploy/frontend.sh 2>&1 >> /home/ubuntu/deploy/frontend.log";
            exec.setCommand(command);

            // 명령어 실행 후 결과를 읽어오는 InputStream
            InputStream inputStream = exec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 채널 연결
            exec.connect();

            // 명령어 실행 결과를 읽어와 콘솔에 출력
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 채널과 세션 닫기
            exec.disconnect();
            session.disconnect();


        }catch( JSchException e ){
            log.error( e.getMessage() );
            throw new UnknowExecException( e );
        } catch (IOException e) {
            log.error( e.getMessage() );
            throw new RuntimeException(e);
        }


    }

    private ChannelExec CreateChannelExec(Session session) {
        try {
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
        return exec;
        } catch (NullPointerException e) {
            log.error( e.getMessage() );
            throw new NullSessionException(e.getMessage());
        } catch (JSchException e) {
            log.error( e.getMessage() );
            throw new ExecConnectionException( e );
//            throw new SftpConnectionException( e.getCause().toString() + e.getMessage() );
        }catch( RuntimeException e ){
            log.error( e.getMessage() );
            throw new SftpRuntimeException( e );
        }

    }
}
