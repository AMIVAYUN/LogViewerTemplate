package com.helper.controlserver.Domain.Log.Infra.Repository;

import com.helper.controlserver.Domain.Log.Infra.Entity.LogSequenceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogSequenceMongoRepository extends MongoRepository<LogSequenceDocument, String> {

}
