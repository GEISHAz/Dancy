package com.ssafy.dancy.video;

import com.ssafy.dancy.CommonSteps;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.entity.Video;
import com.ssafy.dancy.entity.WrongPick;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.repository.WrongPickRepository;
import com.ssafy.dancy.repository.video.VideoRepository;
import com.ssafy.dancy.type.VideoType;
import io.restassured.specification.MultiPartSpecification;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_videoio.*;
import org.opencv.core.CvType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VideoSteps {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private WrongPickRepository wrongPickRepository;
    @Autowired
    private UserRepository userRepository;

    private static final Double score = 94.66;
    private static final String thumbnailImageUrl =
            "https://gumid210bucket.s3.ap-northeast-2.amazonaws.com/thumbnailimage/asap_image_cnh2_uuid.jpg";
    private static final String fullVideoUrl =
            "https://gumid210bucket.s3.ap-northeast-2.amazonaws.com/video/result/asap_result_cnh2_uuid.mp4";

    public Long 결과확인_사전작업(String email){
        User user = userRepository.findByEmail(email).get();

        Video savedVideo = videoRepository.save(Video.builder()
                .user(user)
                .videoTitle("gt_asdf_url.mp4")
                .score(score)
                .thumbnailImageUrl(thumbnailImageUrl)
                .videoType(VideoType.TOTAL)
                .fullVideoUrl(fullVideoUrl)
                .build());

        wrongPickRepository.saveAll(틀린_부분_리스트(savedVideo));
        return savedVideo.getVideoId();
    }

    private List<WrongPick> 틀린_부분_리스트(Video video){
        List<WrongPick> pick = new ArrayList<>();

        pick.add(WrongPick.builder()
                .video(video)
                .startTime(6)
                .endTime(11)
                .accuracy(89.31)
                .build());

        pick.add(WrongPick.builder()
                .video(video)
                .startTime(25)
                .endTime(25)
                .accuracy(92.79)
                .build());

        return pick;
    }


    public static MultiPartSpecification 비디오_생성(String videoFilename){
        try{
            File file = createVideoFile(videoFilename);
            return CommonSteps.createMultipartFileList(file, "videoFile");
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static File createVideoFile(String videoFilename){
        int width = 640;
        int height = 480;
        int fps = 30;

        // 비디오 라이터 생성
        VideoWriter videoWriter = new VideoWriter(videoFilename,
                VideoWriter.fourcc((byte) 'm', (byte) 'p', (byte) '4', (byte) 'v'),
                fps, new Size(width, height), true);

        // 빈 프레임 생성
        Mat frame = new Mat(height, width, CvType.CV_8UC3, new Scalar(0, 0, 0, 0));

        // 지정된 시간만큼 빈 프레임을 반복하여 비디오에 쓰기
        for (int i = 0; i < 100; i++) {
            videoWriter.write(frame);
        }

        // 비디오 라이터 해제
        videoWriter.release();

        // 파일 객체 생성
        return new File(videoFilename);
    }
}
