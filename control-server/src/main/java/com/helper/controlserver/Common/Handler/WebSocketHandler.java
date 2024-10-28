package com.helper.controlserver.Common.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helper.controlserver.Common.WebSocketManager.WebSocketManager;
import com.helper.controlserver.Domain.Log.Domain.Log;
import com.helper.controlserver.Domain.Log.Domain.LogType;
import com.helper.controlserver.Domain.Log.Dto.LogDto;
import com.helper.controlserver.Domain.Log.Infra.Entity.LogSequenceDocument;
import com.helper.controlserver.Domain.Log.Service.LogSequenceService;
import com.helper.controlserver.Domain.Log.Service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final LogService logService;
    private final LogSequenceService logSequenceService;
    private final WebSocketManager manager;
    private final ObjectMapper mapper = new ObjectMapper();



    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

//        System.out.println("get : " + message.getPayload());
        LogDto targetLog = mapper.readValue(message.getPayload(), LogDto.class );

//        Long seq = logSequenceService.genereateSequence( LogType.valueOf( value ) );
//        System.out.println("seq!!!!!!!!!!!" + seq );
        LogSequenceDocument document = logSequenceService.findeSequence( LogType.dev );

        Log log = Log.from( targetLog, document.getValue() );
        document.setValue(document.getValue() + 1 );
        logSequenceService.save(document);
//        System.out.println( log );
        logService.save( log );

        manager.handleTextMessage( session, message );

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        manager.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        manager.removeSession(session);
    }

}
