package uz.md.wordsgeneratorapp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 11:30
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WordGenerateDTO {

//    @Min(value = 0, message = "The value must be positive")
    private int minLength;

//    @Min(value = 0, message = "The value must be positive")
    private int maxLength;

    private String possibleChars;

//    @Min(value = 0L, message = "The value must be positive")
    private int count;

}
