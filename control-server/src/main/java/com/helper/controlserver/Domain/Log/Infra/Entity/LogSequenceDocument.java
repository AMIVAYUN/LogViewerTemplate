package com.helper.controlserver.Domain.Log.Infra.Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document( collection = "log_sequence")
@Getter
@Setter
public class LogSequenceDocument {

    @Id
    private String id;
    @Field
    private Long value;


}
