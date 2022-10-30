package uz.md.wordsgeneratorapp.service.implementations;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.md.wordsgeneratorapp.entity.Job;
import uz.md.wordsgeneratorapp.repository.JobRepository;
import uz.md.wordsgeneratorapp.service.contract.FileService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 21:49
 */

@Service
public class FileServiceImpl implements FileService {

    private final JobRepository jobRepository;

    public FileServiceImpl(
            @Lazy JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    @Async
    public void writeThisToFile(Set<String> words, Job job) {

        System.out.println(" Write to file thread: " + Thread.currentThread().getName());
        String generatedName = String.valueOf(System.currentTimeMillis());
        String filename = "file";
        File file = new File("src/main/resources/static/" + filename + "_" + generatedName + ".txt");
        try {
            file.createNewFile();
            String s = words
                    .stream()
                    .collect(StringBuilder::new, (stringBuilder, s1) -> stringBuilder.append(s1).append("\n"), StringBuilder::append)
                    .toString();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s);
            fileWriter.close();
            job.setFilename(generatedName);
            job.setFinished(true);
            job.setFileSavedTime(LocalDateTime.now());
            jobRepository.save(job);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
