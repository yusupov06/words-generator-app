package uz.md.wordsgeneratorapp.service.contract;

import uz.md.wordsgeneratorapp.entity.Job;
import uz.md.wordsgeneratorapp.payload.JobDTO;
import uz.md.wordsgeneratorapp.payload.WordGenerateDTO;
import uz.md.wordsgeneratorapp.payload.res.ApiResult;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Sat 29/10/22 16:11
 */
public interface JobService {
    Job add(WordGenerateDTO wordGenerateDTO);

    ApiResult<Integer> getHowManyJobsRunning();

    ApiResult<JobDTO> getById(Long id);
}
