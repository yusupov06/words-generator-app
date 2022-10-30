package uz.md.wordsgeneratorapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.md.wordsgeneratorapp.entity.Job;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Sat 29/10/22 16:10
 */
public interface JobRepository extends JpaRepository<Job, Long> {

    Integer countAllByFinishedIsFalse();

}
