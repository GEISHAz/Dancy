import * as V from "./Video.style";
import * as A from "./Accuracy.style";
import React, { useEffect, useRef, useState } from "react";
import PostBtn from "./PostBtn";
import VideoBtn from "./VideoBtn";

export default function Video({ videoInfo, getData }) {
  const videoRef = useRef(null);
  const [errorList, setErrorList] = useState([]);

  useEffect(() => {
    setErrorList(videoInfo.list);
  }, []);

  const handlePlayButtonClick = (start, end) => {
    console.log(start, end);

    // 기존의 timeupdate 이벤트 리스너 제거
    videoRef.current.removeEventListener('timeupdate', handleTimeUpdate);
    // 기존의 ended 이벤트 리스너 제거
    videoRef.current.removeEventListener('ended', handleVideoEnded)

    // 비디오를 일시정지
    videoRef.current.pause();

    // currentTime을 변경하고 다시 재생
    videoRef.current.currentTime = start;
    videoRef.current.play();

    // 새로운 timeupdate 이벤트 리스너 등록
    videoRef.current.addEventListener("timeupdate", () =>
      handleTimeUpdate(start, end)
    );
    // ended 이벤트에 대한 리스너 등록
  videoRef.current.addEventListener('ended', () => handleVideoEnded(start));
  };

  // ended 이벤트 핸들러
  const handleVideoEnded = (start) => {
    videoRef.current.currentTime = start;
  };

  // timeupdate 이벤트 핸들러
  const handleTimeUpdate = (start, end) => {
    if (end >= videoRef.current.duration) {
        end = videoRef.current.duration
      }

    if (videoRef.current.currentTime >= end) {
      // end 지점에 도달하면 시작구간으로 되돌아감
      videoRef.current.currentTime = start;
    }
  };

  // 초를 분:초 형태의 문자열로 변환하는 함수
  const formatTime = (timeInSeconds) => {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = Math.floor(timeInSeconds % 60);
    return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  };

  const handleContinueButtonClick = () => {
    console.log('click')
    // 기존의 timeupdate 및 ended 이벤트 리스너 제거
    videoRef.current.removeEventListener('timeupdate', handleTimeUpdate);
    videoRef.current.removeEventListener('ended', handleVideoEnded);
  
    // 비디오를 일시정지하고 처음으로 되돌리기
    videoRef.current.pause();
    videoRef.current.currentTime = 0;
  
    // 비디오 재생 시작
    videoRef.current.play();
  };

  return (
    <V.Wrap>
      <V.VideoNBtns>
        <V.VideoArea ref={videoRef} controls loops>
          <source src={videoInfo.totalUrl} type="video/mp4" />
        </V.VideoArea>

        <VideoBtn avgAccuracy={videoInfo.total_accuracy} />
      </V.VideoNBtns>

      <V.AccuracyNPost>
        <A.BgImg>
          <A.SectionInfo>
            {errorList.map((errorItem, index) => (
              <div key={index} className="flex gap-x-3">
                <A.ErrorIdx>{index + 1}</A.ErrorIdx>
                <div className="flex gap-x-5">
                  <A.ErrorSec
                    onClick={() =>
                      handlePlayButtonClick(
                        errorItem.start - 2,
                        errorItem.end + 2
                      )
                    }
                  >
                    {/* {errorItem.start} ~ {errorItem.end} */}
                    {`${formatTime(errorItem.start)} ~ ${formatTime(errorItem.end)}`}
                  </A.ErrorSec>
                  <A.ErrorAccu>{errorItem.accuracy}</A.ErrorAccu>
                </div>
              </div>
            ))}
          </A.SectionInfo>
          <A.ContinueBtn onClick={() => handleContinueButtonClick()}>
            연속 재생
          </A.ContinueBtn>
        </A.BgImg>
        <PostBtn getData={getData} />
      </V.AccuracyNPost>
    </V.Wrap>
  );
}
