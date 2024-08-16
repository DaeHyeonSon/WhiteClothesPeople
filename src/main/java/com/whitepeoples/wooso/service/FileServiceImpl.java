package com.whitepeoples.wooso.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

	@Override
	public Optional<String> uploadFile(MultipartFile file) throws Exception {
	    Optional<String> res;
	    String fileUri = null;
	    try {
	        Path uploadDir = Paths.get(UPLOAD_DIR);
	        if (!Files.exists(uploadDir)) {
	            Files.createDirectories(uploadDir);
	        }

	        String originalFilename = file.getOriginalFilename();
	        if (originalFilename != null && !originalFilename.isEmpty()) {
	            // 파일 확장자 추출
	            String fileExtension = StringUtils.getFilenameExtension(originalFilename);
	            
	            // UUID를 사용하여 새 파일 이름 생성
	            String newFilename = UUID.randomUUID().toString();
	            if (fileExtension != null && !fileExtension.isEmpty()) {
	                newFilename += "." + fileExtension;
	            }

	            Path filePath = uploadDir.resolve(newFilename);
	            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	            // fileUri 설정 (/uploads/ 포함)
	            fileUri = "/uploads/" + newFilename;
	        }
	        res = Optional.ofNullable(fileUri);
	        return res;
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new Exception("현재 서버에서 파일을 업로드하는데 문제가 발생했습니다.");
	    }
	}

}
