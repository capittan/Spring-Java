package program.controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.dto.uploadImage.UploadImageDTO;
import program.storage.StorageService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class HomeController {
    private final StorageService storageService;

    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ResponseBody
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serverFile(@PathVariable String filename) throws Exception {
        Resource file = storageService.loadAsResources(filename);// getting file
        String urlFileName = URLEncoder.encode("cat.jpg", StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + urlFileName + "\"")
                .body(file);
    }

    @PostMapping("/upload")
    public String upload(@RequestBody UploadImageDTO dto) {
        String fileSave = storageService.save(dto.getBase64());
        return fileSave;
    }
}
