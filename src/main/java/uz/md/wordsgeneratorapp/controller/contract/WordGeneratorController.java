package uz.md.wordsgeneratorapp.controller.contract;

import org.springframework.web.bind.annotation.*;
import uz.md.wordsgeneratorapp.payload.JobDTO;
import uz.md.wordsgeneratorapp.payload.WordGenerateDTO;
import uz.md.wordsgeneratorapp.payload.res.ApiResult;

import javax.validation.Valid;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Wed 26/10/22 22:34
 */

@RequestMapping(path = WordGeneratorController.BASE_PATH)
public interface WordGeneratorController {

    String BASE_PATH = "api/generator";
    String GENERATE_WORDS_PATH = "/generate/word";
    String STAT_RUNNING_JOBS_PATH = "/job/stat/running";
    String GET_JOB_BY_ID_PATH = "/job/{id}";

    @PostMapping(value = GENERATE_WORDS_PATH)
    ApiResult<?> generateWords(@Valid @RequestBody WordGenerateDTO wordGenerateDTO);

    @GetMapping(value = GET_JOB_BY_ID_PATH)
    ApiResult<JobDTO> getJobById(@PathVariable Long id);

    @GetMapping(value = STAT_RUNNING_JOBS_PATH)
    ApiResult<?> howManyJobs();

}
