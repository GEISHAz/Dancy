package com.ssafy.dancy.util;

import com.ssafy.dancy.type.CustomMultipartFile;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class VideoProcessor {

    public MultipartFile captureThumbnailFromVideo(MultipartFile file, int frameNumber, String filename){
        byte[] imageAsByte = captureFrame(file, frameNumber);
        return new CustomMultipartFile(imageAsByte, filename, "image/jpg");
    }

    public byte[] captureFrame(MultipartFile file, int frameNumber) {
        try{
            File tempFile = File.createTempFile("video", "mp4");
            file.transferTo(tempFile);

            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(tempFile);
            grabber.start();

            grabber.setFrameNumber(frameNumber);
            Frame frame = grabber.grabImage();

            if(frame != null) {
                Java2DFrameConverter frameConverter = new Java2DFrameConverter();
                BufferedImage bufferedImage = frameConverter.convert(frame);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", stream);
                return stream.toByteArray();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
