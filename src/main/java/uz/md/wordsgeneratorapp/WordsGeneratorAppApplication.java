package uz.md.wordsgeneratorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WordsGeneratorAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordsGeneratorAppApplication.class, args);
    }

}
