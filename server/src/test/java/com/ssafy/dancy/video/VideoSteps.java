package com.ssafy.dancy.video;

import com.ssafy.dancy.CommonSteps;
import io.restassured.specification.MultiPartSpecification;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class VideoSteps {

    public static MultiPartSpecification 레퍼런스_비디오_생성(){
        try{
            String path = "C:/Users/SSAFY/Videos/asap.mp4";
            File file = new File(path);

            return CommonSteps.createMultipartFileList(file, "videoFile");
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static MultiPartSpecification 연습_비디오_생성(){
        try{
            String path = "C:/Users/SSAFY/Videos/asdf.mp4";
            File file = new File(path);

            return CommonSteps.createMultipartFileList(file, "videoFile");
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
