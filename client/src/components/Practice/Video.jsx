import * as V from "./Video.style";
import * as A from "./Accuracy.style";
import React, { useEffect, useRef, useState } from "react";
import { useRecoilState, useRecoilValue } from "recoil";
import { practiceState, resultState } from "../../recoil/PracticeState";
import { analyzeResult } from "../../api/video";
import PostBtn from "./PostBtn";
import VideoBtn from "./VideoBtn";

export default function Video({ getData }) {
  const videoRef = useRef(null);
  const [errorList, setErrorList] = useState([]);

  const [loopStart, setLoopStart] = useState(0);
  const [loopEnd, setLoopEnd] = useState(0);
	
	const transVideo = useRecoilValue(resultState)
	const [videoInfo, setVideoInfo] = useRecoilState(practiceState);
	const [totalDuration, setTotalDuration] = useState('')
	
  const [intervalId, setIntervalId] = useState(null);

  useEffect(() => {
    console.log(transVideo.videoId)
		analyzeResult(transVideo.videoId)
		.then((res) => {
      console.log(res)
			setVideoInfo(res)
      return res
		})
    .then((res) => {
      setErrorList(res.wrongSections);
    })
  }, [transVideo])


  useEffect(() => {
		console.log(videoInfo)
    setErrorList(videoInfo.wrongSections);
		handleVideoDuration()
		}, []);


    // 비디오 전체 길이를 확인하는 함수
  const handleVideoDuration = () => {
    setTotalDuration(videoRef.current.duration);
	};
	const handleTimeUpdateRef = useRef(null);

	// 구간 반복을 지정하는 함수
	const handlePlayButtonClick = (start, end) => {
		// 이전에 추가한 이벤트 리스너를 제거
		if (handleTimeUpdateRef.current) {
			videoRef.current.removeEventListener('timeupdate', handleTimeUpdateRef.current);
		}
	
		const handleTimeUpdate = () => {
			if (videoRef.current.currentTime >= end) {
				videoRef.current.currentTime = start;
			}
		}
		
		// 현재 handleTimeUpdate 함수를 참조로 저장
		handleTimeUpdateRef.current = handleTimeUpdate;
	
		videoRef.current.pause();
	
		if (start !== 0 || end !== 0) {
			videoRef.current.currentTime = start;
			videoRef.current.addEventListener('timeupdate', handleTimeUpdateRef.current);
		} else {
			videoRef.current.currentTime = 0;
		}
		
		videoRef.current.play();
	};
	
  // 초를 분:초 형태의 문자열로 변환하는 함수
  const formatTime = (timeInSeconds) => {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = Math.floor(timeInSeconds % 60);
    return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  };

  return (
    <V.Wrap>
      <V.VideoNBtns>
        <V.VideoArea src={videoInfo.videoUrl} ref={videoRef} controls />
        <VideoBtn avgAccuracy={videoInfo.score} />
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
          <A.ContinueBtn onClick={() => handlePlayButtonClick(0, 0)}>
            연속 재생
          </A.ContinueBtn>
        </A.BgImg>
        <PostBtn getData={getData} />
      </V.AccuracyNPost>
    </V.Wrap>
  );
}
