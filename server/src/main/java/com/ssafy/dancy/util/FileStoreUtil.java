package com.ssafy.dancy.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileStoreUtil {

    private final AwsS3Util s3Util;

    public String uploadProfileImageToS3(MultipartFile file, String targetDirectory){
        if(file == null){
            return null;
        }

        String storeImageFileName = createStoreProfileImageName(file.getOriginalFilename());
        return uploadFileToS3(file, targetDirectory, storeImageFileName);
    }

    public String uploadThumbnailImageToS3(MultipartFile file, String targetDirectory, String storeFilename){
        return uploadFileToS3(file, targetDirectory, storeFilename);
    }

    public String uploadVideoFileToS3(MultipartFile file, String targetDirectory, String storeFilename) {
        return uploadFileToS3(file, targetDirectory, storeFilename);
    }


    public String uploadFileToS3(MultipartFile file, String targetDirectory, String storeFilename){
        String storeFilePath = String.format("%s/%s", targetDirectory, storeFilename);

        try {
            return s3Util.uploadToAWS(file, storeFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createStoreProfileImageName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    public String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");
        return originalFilename.substring(position + 1);
    }
}
