package com.ssafy.dancy.user;

import com.ssafy.dancy.message.request.user.NicknameRequest;
import com.ssafy.dancy.type.CustomMultipartFile;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class UserSteps {

    public static final String toChangeNickname = "asdf";

    public NicknameRequest 닉네임_변경요청_생성(String nickname){
        return NicknameRequest.builder()
                .nickname(nickname)
                .build();
    }

    public static MultiPartSpecification 프로필_이미지_간단생성(){
        try{
            String fileFullName = "image.png";
            File file = new File(System.getProperty("java.io.tmpdir"), fileFullName);

            BufferedImage image = new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
            ImageIO.write(image,"png",file);

            return createMultipartFileList(file, "profileImage");
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private static MultiPartSpecification createMultipartFileList(File file, String fieldName) throws IOException {
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
