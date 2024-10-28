package com.helper.controlserver.Domain.Log.Domain;


import com.helper.controlserver.Domain.Log.Dto.LogDto;
import com.helper.controlserver.Domain.Log.Infra.Entity.LogDocument;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Log {
    private Long id;
    private String content;
    private Date date;
    private LogType type;

    public static Log from(LogDto targetLog, Long id) {
        return Log.builder()
                .id( id )
                .content(targetLog.getContent())
                .date(targetLog.getDate())
                .type(targetLog.getType())
                .build();
    }

    public static Log from(LogDto targetLog) {
        return Log.builder()
                .content(targetLog.getContent())
                .date(targetLog.getDate())
                .type(targetLog.getType())
                .build();
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", type=" + type +
                '}';
    }

    public Log(Long id, String content, Date date, LogType type) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public Log(String content, Date date, LogType type) {
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public Log(LogDocument document ){
        this.id = document.getId();
        this.content = document.getContent();
        this.date = document.getDate();
        this.type = document.getType();
    }


}
