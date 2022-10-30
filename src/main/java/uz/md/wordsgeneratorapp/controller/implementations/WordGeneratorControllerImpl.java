package uz.md.wordsgeneratorapp.controller.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.md.wordsgeneratorapp.controller.contract.WordGeneratorController;
import uz.md.wordsgeneratorapp.payload.JobDTO;
import uz.md.wordsgeneratorapp.payload.WordGenerateDTO;
import uz.md.wordsgeneratorapp.payload.res.ApiResult;
import uz.md.wordsgeneratorapp.service.contract.JobService;
import uz.md.wordsgeneratorapp.service.contract.WordGeneratorService;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Wed 26/10/22 22:34
 */

@RestController
@RequiredArgsConstructor
public class WordGeneratorControllerImpl implements WordGeneratorController {

    private final WordGeneratorService wordGeneratorService;
    private final JobService jobService;

    @Override
    public ApiResult<?> generateWords(WordGenerateDTO wordGenerateDTO) {
        return wordGeneratorService.generate(wordGenerateDTO);
    }

    @Override
    public ApiResult<JobDTO> getJobById(Long id) {
        return jobService.getById(id);
    }

    @Override
    public ApiResult<?> howManyJobs() {
        return jobService.getHowManyJobsRunning();
    }
}
