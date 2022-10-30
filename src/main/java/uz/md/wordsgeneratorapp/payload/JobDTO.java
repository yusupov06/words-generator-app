package uz.md.wordsgeneratorapp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.md.wordsgeneratorapp.entity.Job;

import java.time.LocalDateTime;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Sun 30/10/22 22:13
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDTO {
    private Long id;
    private int minLength;
    private int maxLength;
    private String possibleChars;
    private int count;
    private String filename;
    private boolean finished;
    private LocalDateTime requestTime;
    private LocalDateTime fileSavedTime;

    public JobDTO(Job job) {
        this.id = job.getId();
        this.minLength = job.getMinLength();
        this.maxLength = job.getMaxLength();
        this.possibleChars = job.getPossibleChars();
        this.count = job.getCount();
        this.filename = job.getFilename();
        this.finished = job.isFinished();
        this.requestTime = job.getRequestTime();
        this.fileSavedTime = job.getFileSavedTime();
    }
}
