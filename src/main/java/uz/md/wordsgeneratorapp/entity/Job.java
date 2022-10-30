package uz.md.wordsgeneratorapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Sat 29/10/22 16:08
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int minLength;
    private int maxLength;
    private String possibleChars;
    private int count;

    private String filename;

    private boolean finished;

    private LocalDateTime requestTime;
    private LocalDateTime fileSavedTime;

    public Job(int minLength, int maxLength, char[] possibleChars, int count) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.possibleChars = String.valueOf(possibleChars);
        this.count = count;
        this.requestTime = LocalDateTime.now();
    }
}
