package uz.md.wordsgeneratorapp.controller.contract;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 22:05
 */

@RequestMapping(DownloadController.BASE_PATH)
public interface DownloadController {
    String BASE_PATH = "api/generator";
    String DOWNLOAD_PATH = "/download/{name}";

    @GetMapping(DOWNLOAD_PATH)
    ResponseEntity<Resource> downloadFileFromLocal(@PathVariable String name);

}
