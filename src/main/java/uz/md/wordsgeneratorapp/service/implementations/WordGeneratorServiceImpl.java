package uz.md.wordsgeneratorapp.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.md.wordsgeneratorapp.entity.Job;
import uz.md.wordsgeneratorapp.payload.WordGenerateDTO;
import uz.md.wordsgeneratorapp.payload.res.ApiResult;
import uz.md.wordsgeneratorapp.payload.res.ErrorData;
import uz.md.wordsgeneratorapp.repository.JobRepository;
import uz.md.wordsgeneratorapp.service.contract.FileService;
import uz.md.wordsgeneratorapp.service.contract.JobService;
import uz.md.wordsgeneratorapp.service.contract.WordGeneratorService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 21:03
 */

@Service
@RequiredArgsConstructor
public class WordGeneratorServiceImpl implements WordGeneratorService {

    private final FileService fileService;
    private final JobService jobService;
    private final JobRepository jobRepository;

    @Override
    public ApiResult<?> generate(WordGenerateDTO wordGenerateDTO) {

        if (wordGenerateDTO.getMinLength() > wordGenerateDTO.getMaxLength()) {
            int t = wordGenerateDTO.getMinLength();
            wordGenerateDTO.setMinLength(wordGenerateDTO.getMaxLength());
            wordGenerateDTO.setMaxLength(t);
        }

        List<String> errors = checkForPossibleToGenerate(wordGenerateDTO);
        if (!errors.isEmpty()) {
            return ApiResult.failResponse(
                    errors
                            .stream()
                            .map(ErrorData::new)
                            .collect(Collectors.toList())
            );
        }

        Job job = jobService.add(wordGenerateDTO);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> generateWords(job));

        System.out.println(Thread.currentThread().getName());
        return ApiResult.successResponse(" Job received ");
    }

    private List<String> checkForPossibleToGenerate(WordGenerateDTO wordGenerateDTO) {
        int minLength = wordGenerateDTO.getMinLength();
        int maxLength = wordGenerateDTO.getMaxLength();
        int count = wordGenerateDTO.getCount();
        char[] chars = wordGenerateDTO.getPossibleChars().toCharArray();
        List<String> errors = new ArrayList<>();
        if (count <= 0) errors.add("Count must be positive");
        if (minLength < 0 || maxLength < 0)
            errors.add(" minimum and maximum length must be positive");
        if (minLength > chars.length) errors.add("invalid minimum length " + minLength);

        System.out.println(" checkForPossible  = " + Thread.currentThread().getName());

        int possibleCounts = evaluatePossibleCounts(minLength, maxLength, chars.length);
        System.out.println("possibleCounts = " + possibleCounts);
        if (possibleCounts < wordGenerateDTO.getCount()) {
            errors.add(" count may be more than possible counts");
        }

        return errors;
    }

    private int evaluatePossibleCounts(int min, int max, int length) {
        int possibleCount = 0;
        for (int i = min; i <= (Math.min(max, length)); i++) {
            possibleCount += Math.pow(length, i);
        }
        return possibleCount;
    }

    @Async
    public void generateWords(Job job) {

        System.out.println(" Generate = " + Thread.currentThread().getName());
        Set<String> set = new HashSet<>();
        Random random = new Random();
        int count = job.getCount();
        char[] chars = job.getPossibleChars().toCharArray();
        int min = job.getMinLength();
        int max = job.getMaxLength();
        int cLoop = 0;

        try {
            while (set.size() < count) {
                cLoop++;
                int l = random.nextInt(min, max + 1);
                String s = randomWord(l, chars).get();
                set.add(s);
                System.out.println("set = " + set.size());
                System.out.println("cLoop = " + cLoop);
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("cLoop = " + cLoop);
        System.out.println("set = " + set.size());
        fileService.writeThisToFile(set, job);
    }

    @Async
    public CompletableFuture<String> randomWord(int length, char[] possibleChars) {
        Random random = new Random();
        return CompletableFuture.completedFuture(random.ints(length, 0, possibleChars.length)
                .parallel()
                .collect(StringBuilder::new, (stringBuilder, value) ->
                        stringBuilder.append(possibleChars[value]), StringBuilder::append)
                .toString());
    }


}
