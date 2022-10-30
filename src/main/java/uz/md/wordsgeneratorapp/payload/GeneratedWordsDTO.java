package uz.md.wordsgeneratorapp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 20:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneratedWordsDTO {

    private Set<String> generatedWords;
    private LocalDateTime createdAt;

}
