package com.helper.controlserver.Domain.Log.Infra.Repository;


import com.helper.controlserver.Domain.Log.Infra.Entity.LogDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMongoRepository extends MongoRepository<LogDocument, String> {


    List<LogDocument> findAll();

    @Override
    Page<LogDocument> findAll(Pageable pageable);

    @Query("{ '_id' : { $gt: ?0 } }")
    List<LogDocument> findAllWithSlice( Long id, Pageable pageable );
}

