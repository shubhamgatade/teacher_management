package com.teacher.main.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teacher.main.common.service.UploadService;

@RestController
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	private UploadService uploadService;

	@PostMapping("/teacher")
    public String uploadTransitTime(@RequestParam("requestFile") MultipartFile excelFile) throws Exception {

        return uploadService.processTeachersCreationUsingFile(excelFile);
    }
}
