package uz.md.wordsgeneratorapp.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.md.wordsgeneratorapp.entity.Job;
import uz.md.wordsgeneratorapp.exception.JobNotFoundException;
import uz.md.wordsgeneratorapp.payload.JobDTO;
import uz.md.wordsgeneratorapp.payload.WordGenerateDTO;
import uz.md.wordsgeneratorapp.payload.res.ApiResult;
import uz.md.wordsgeneratorapp.repository.JobRepository;
import uz.md.wordsgeneratorapp.service.contract.JobService;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Sat 29/10/22 16:11
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;


    @Override
    public Job add(WordGenerateDTO wordGenerateDTO) {

        int count = wordGenerateDTO.getCount();
        int minLength = wordGenerateDTO.getMinLength();
        int maxLength = wordGenerateDTO.getMaxLength();

        if (minLength > maxLength) {
            int temp = minLength;
            minLength = maxLength;
            maxLength = temp;
        }

        char[] possibleChars = wordGenerateDTO.getPossibleChars().toCharArray();
        return jobRepository.save(new Job(minLength, maxLength, possibleChars, count));
    }

    @Override
    public ApiResult<Integer> getHowManyJobsRunning() {
        Integer notFinished = jobRepository.countAllByFinishedIsFalse();
        return ApiResult.successResponse(notFinished);
    }

    @Override
    public ApiResult<JobDTO> getById(Long id) {
        return ApiResult.successResponse(
                new JobDTO(jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("JOB NOT FOUND WITH ID " + id))));
    }
}
