package com.ssafy.dancy.type;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

public enum ReferenceType {

    IMAGE(List.of("jpg","jpeg","png","svg","gif")),
    VIDEO(List.of("mp4", "mov","wmv","avi","flv", "mkv")),
    TEST(List.of("txt","mp4"));

    // enum 을 통해 관리되어야 하는 것
    //  1. 받을 수 있는 파일타입

    private List<String> filetypeList;


    ReferenceType(List<String> filetypeList) {
        this.filetypeList = filetypeList;
    }

    public static boolean isValidFileType(ReferenceType referenceType, List<MultipartFile> files){

        for(MultipartFile file : files){
            String ext = extractExt(file.getOriginalFilename());
            if(!referenceType.filetypeList.contains(ext)){
                return false;
            }
        }
        return true;
    }

    public static String extractExt(String originalFilename){
        int position = originalFilename.lastIndexOf(".");
        return originalFilename.substring(position + 1);
    }
}
