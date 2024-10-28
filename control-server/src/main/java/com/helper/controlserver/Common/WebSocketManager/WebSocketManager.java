package com.helper.controlserver.Common.WebSocketManager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WebSocketManager {

    private final List<WebSocketSession> sessionList = new ArrayList<>();

    public void addSession( WebSocketSession session ){
        sessionList.add( session );
    }

    public void removeSession( WebSocketSession session ){
        sessionList.remove( session );
    }

    public List<WebSocketSession> findAll(){
        return sessionList;
    }

    public void handleTextMessage( WebSocketSession origin, TextMessage message){
        for( WebSocketSession session : sessionList){
            if( session.isOpen() && !session.equals( origin ) ) {
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    log.warn("웹 소켓 관리간 에러 발생");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
