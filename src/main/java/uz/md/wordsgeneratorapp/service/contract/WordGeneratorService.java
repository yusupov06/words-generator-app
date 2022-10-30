package uz.md.wordsgeneratorapp.service.contract;

import uz.md.wordsgeneratorapp.payload.WordGenerateDTO;
import uz.md.wordsgeneratorapp.payload.res.ApiResult;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 11:39
 */

public interface WordGeneratorService {
    ApiResult<?> generate(WordGenerateDTO wordGenerateDTO);

}
