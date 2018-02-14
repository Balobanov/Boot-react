package com.balobanov.controllers.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping(value = "/files")
public class FileUploadController {

    @Value("${multipart.upload.files.location}")
    private String uploadFolder;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void uploadFile(@RequestBody MultipartFile file) throws IOException {
        String s = StringUtils.cleanPath(file.getOriginalFilename());
        Files.copy(file.getInputStream(), Paths.get(uploadFolder).resolve(s), StandardCopyOption.REPLACE_EXISTING);
    }
}
