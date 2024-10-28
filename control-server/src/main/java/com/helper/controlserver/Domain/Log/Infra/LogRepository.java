package com.helper.controlserver.Domain.Log.Infra;

import com.helper.controlserver.Domain.Log.Domain.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LogRepository {
    List<Log> findAll();
    List<Log> findAllByTypeOrderById( String logType );
    void save( Log log );
    List<Log> findWithPage(int page, int size );

    List<Log> findAllByIdWithPage(Long id, Pageable pageable);
}
