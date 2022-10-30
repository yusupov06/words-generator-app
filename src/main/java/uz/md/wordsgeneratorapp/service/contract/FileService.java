package uz.md.wordsgeneratorapp.service.contract;

import uz.md.wordsgeneratorapp.entity.Job;

import java.util.Set;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 21:49
 */

public interface FileService {
    void writeThisToFile(Set<String> words, Job job);

}
