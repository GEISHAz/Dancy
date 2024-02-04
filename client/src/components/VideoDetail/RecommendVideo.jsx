import React from "react";
import Card from '../Stage/Card';
import {
  RecommendVideoContainer,
  RecommendVideoTitleContainer,
  RecommendVideoTitle,
  RecommendVideos,
  RecommendVideoTitleLine,
} from './RecommendVideo.style';


export default function RecommendVideo() {
  return (
    <RecommendVideoContainer>
      <RecommendVideoTitleContainer>
        <RecommendVideoTitle>추천영상</RecommendVideoTitle>
        <RecommendVideoTitleLine />
      </RecommendVideoTitleContainer>
      <RecommendVideos>
        <Card/>
      </RecommendVideos>
    </RecommendVideoContainer>
  );
}

