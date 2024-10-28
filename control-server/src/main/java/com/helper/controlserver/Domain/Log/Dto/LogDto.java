package com.helper.controlserver.Domain.Log.Dto;


import com.helper.controlserver.Domain.Log.Domain.Log;
import com.helper.controlserver.Domain.Log.Domain.LogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {
    public Long id;
    public String content;
    public Date date;
    public LogType type;
    public String ip;

    public static LogDto from(Log log) {
        LogDto dto = new LogDto();
        dto.id=log.getId();
        dto.content = log.getContent();
        dto.date = log.getDate();
        dto.type = log.getType();
        return dto;
    }
}
