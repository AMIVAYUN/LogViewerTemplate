package com.helper.controlserver.Domain.Log.Infra.Entity;

import com.helper.controlserver.Domain.Log.Domain.Log;
import com.helper.controlserver.Domain.Log.Domain.LogType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Document( collection = "log")
public class LogDocument {

    @Id
    private Long id;
    private String content;
    private Date date;
    private LogType type;


    public static LogDocument from(Log log ) {

        LogDocument document = new LogDocument();
        document.id = log.getId();
        document.content = log.getContent();
        document.date = log.getDate();
        document.type=log.getType();

        return document;
    }
}
