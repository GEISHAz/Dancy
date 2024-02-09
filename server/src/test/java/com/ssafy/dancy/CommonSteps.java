package com.ssafy.dancy;

import com.ssafy.dancy.type.CustomMultipartFile;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CommonSteps {

    public static MultiPartSpecification createMultipartFileList(File file, String fieldName) throws IOException {
        DiskFileItem fileItem = new DiskFileItem("file", "application/octet-stream", false, file.getName(), (int) file.length() , file.getParentFile());
        fileItem.getOutputStream().write(Files.readAllBytes(file.toPath()));

        MultipartFile multipartFile = new CustomMultipartFile(fileItem.get(),fileItem.getName());
        return new MultiPartSpecBuilder(multipartFile.getBytes())
                .controlName(fieldName)
                .fileName(multipartFile.getOriginalFilename())
                .mimeType(multipartFile.getContentType())
                .build();
    }
}
