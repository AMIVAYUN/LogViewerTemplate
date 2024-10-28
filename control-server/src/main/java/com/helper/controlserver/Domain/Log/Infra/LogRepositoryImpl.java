package com.helper.controlserver.Domain.Log.Infra;


import com.helper.controlserver.Domain.Log.Domain.Log;
import com.helper.controlserver.Domain.Log.Infra.Entity.LogDocument;
import com.helper.controlserver.Domain.Log.Infra.Repository.LogMongoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LogRepositoryImpl implements LogRepository {

    private final LogMongoRepository logMongoRepository;

    @Override
    public List<Log> findAll() {

        return logMongoRepository.findAll().stream().map(Log::new).collect(Collectors.toList());
    }

    @Override
    public List<Log> findAllByTypeOrderById( String type) {
        return logMongoRepository.findAll().stream().map(Log::new).collect(Collectors.toList());
    }

    @Override
    public void save( Log log ) {
        logMongoRepository.save(LogDocument.from( log ) );

    }

    public List<Log> findWithPage(int page, int size ){
        Pageable pageable = PageRequest.of( page, size, Sort.by(Sort.Direction.DESC,"_id") );
        Page<LogDocument> documents = logMongoRepository.findAll( pageable );
        return documents.stream().map( Log::new ).collect(Collectors.toList());
    }

    @Override
    public List<Log> findAllByIdWithPage( Long id, Pageable pageable) {
        List<LogDocument> documents = logMongoRepository.findAllWithSlice( id, pageable );
        return documents.stream().map( Log::new).collect(Collectors.toList());
    }

}
