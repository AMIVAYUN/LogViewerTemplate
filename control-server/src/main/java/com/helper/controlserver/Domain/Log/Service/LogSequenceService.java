package com.helper.controlserver.Domain.Log.Service;


import com.helper.controlserver.Domain.Log.Domain.LogType;
import com.helper.controlserver.Domain.Log.Infra.Repository.LogSequenceMongoRepository;
import com.helper.controlserver.Domain.Log.Infra.Entity.LogSequenceDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Sequence;

@RequiredArgsConstructor
@Service
public class LogSequenceService {
    /**
     * Log Service와 다르게 Sequence Generator는 기본적으로 MongoDB 종속적이기에 구현체를 바로 등록.
     */

    private final LogSequenceMongoRepository repository;

    @Transactional
    public LogSequenceDocument findeSequence(LogType logType){
        LogSequenceDocument document = repository.findById( logType.toString() ).get();
        return document;

    }
    @Transactional
    public void save(LogSequenceDocument document ){
        repository.save(document);

    }


}
