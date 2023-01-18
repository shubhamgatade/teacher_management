package com.teacher.main.common.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	String processTeachersCreationUsingFile(MultipartFile uploadedFile) throws Exception;
}
