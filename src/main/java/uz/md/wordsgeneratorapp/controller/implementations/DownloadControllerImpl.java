package uz.md.wordsgeneratorapp.controller.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.md.wordsgeneratorapp.controller.contract.DownloadController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Me: muhammadqodir
 * Project: words-generator-app/IntelliJ IDEA
 * Date:Fri 28/10/22 22:06
 */

@RestController
@RequiredArgsConstructor
public class DownloadControllerImpl implements DownloadController {

    private final String FILE_BASE_PATH = "src/main/resources/static/";

    @Override
    public ResponseEntity<Resource> downloadFileFromLocal(String name) {

        Path path = Paths.get(FILE_BASE_PATH + name);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
