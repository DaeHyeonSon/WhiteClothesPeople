package com.whitepeoples.wooso;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String filename = file.getOriginalFilename();
            if (filename != null) {
                Path filePath = uploadDir.resolve(filename);
                Files.copy(file.getInputStream(), filePath);

                String fileUri = "/uploads/" + filename;
                return ResponseEntity.ok("File uploaded successfully. File URL: " + fileUri);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file name.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }
    }
}