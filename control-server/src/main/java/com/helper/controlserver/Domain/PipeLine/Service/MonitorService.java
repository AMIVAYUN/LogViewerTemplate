package com.helper.controlserver.Domain.PipeLine.Service;


import com.helper.controlserver.Domain.PipeLine.Dto.JobDto;
import com.helper.controlserver.Domain.PipeLine.Exception.Monitor.AutomatingSequenceTransferException;
import com.helper.controlserver.Domain.PipeLine.Exception.Monitor.JobNotFoundException;
import com.helper.controlserver.Domain.PipeLine.Exception.Monitor.MergingSequenceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class MonitorService {

    @Value("${secret.joburl}")
    private String jobUrl;

    @Value(("${secret.token}"))
    private String token;


    public List<JobDto> getJobs() {
        WebClient client = WebClient.create(jobUrl);
        List<JobDto> ans = client.get()
                .header("PRIVATE-TOKEN", token )
                .retrieve()
                .bodyToFlux(JobDto.class)
                .collectList().block();
        if( ans.size() == 0 ) throw new JobNotFoundException();
        return ans;
    }




    public JobDto FindMergingSequence( boolean isBe, List<JobDto>lst ){
        String target = isBe ? "merge_be_dev" : "merge_fe_dev";
        for( JobDto dto: lst ){
            if( dto.getName().equals( target )){
                return dto;
            }
        }

        throw new MergingSequenceNotFoundException();
    }

    public boolean ActivatingJob(boolean isBe, JobDto sequence) {


        String actUrl = jobUrl+"/"+sequence.getId()+"/play";
        WebClient client = WebClient.create( actUrl );
        ClientResponse result = client.post()
                .header( "PRIVATE-TOKEN", token  )
                .exchange()
                .block();
        HttpStatusCode status = result.statusCode();
        if( status.is2xxSuccessful() ){
            return true;
        }else{
            throw new AutomatingSequenceTransferException( (isBe ? "Backend":"Frontend") + result.statusCode() + ": " + result.bodyToMono( String.class ) );
        }
    }
}
