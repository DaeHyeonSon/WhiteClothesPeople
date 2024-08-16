package com.whitepeoples.wooso.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public interface FileService {
	  
	public Optional<String> uploadFile(MultipartFile file) throws Exception;
	
}
