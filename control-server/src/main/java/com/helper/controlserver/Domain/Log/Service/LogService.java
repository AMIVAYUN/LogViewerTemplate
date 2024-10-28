package com.helper.controlserver.Domain.Log.Service;

import com.helper.controlserver.Domain.Log.Domain.Log;
import com.helper.controlserver.Domain.Log.Exception.LogSelectionErrorException;
import com.helper.controlserver.Domain.Log.Infra.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {
    private final LogRepository repository;
    private final LogSequenceService sequenceService;

    public List<Log> findAll(){
        return repository.findAll();
    }


    public void save( Log log ){
        repository.save( log );
    }


    public void test() throws InterruptedException {
        Thread.sleep(5000);
    }


    public List<Log> findAllWithPaging( int size, int page ){
        return repository.findWithPage(size,page);
    }


    public List<Log> findAllWithIdAndSlice( Long id, int size ){
        try{
            Pageable pageable = PageRequest.of(0,size, Sort.Direction.ASC,"_id" );
            List<Log> returnValue = repository.findAllByIdWithPage( id - size, pageable );
            return returnValue;
        }catch( RuntimeException e ){
            log.info("로그 조회간 에러 발생" + e.toString() );
            throw new LogSelectionErrorException( e.getMessage() );
        }

    }


}
