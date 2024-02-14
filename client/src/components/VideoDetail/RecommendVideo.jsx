import React from "react";
import Card from '../Stage/Card';
import { useNavigate } from "react-router-dom";
import {
  RecommendVideoContainer,
  RecommendVideoTitleContainer,
  RecommendVideoTitle,
  RecommendVideos,
  RecommendVideoTitleLine,
} from './RecommendVideo.style';



export default function RecommendVideo() {
  // const navigate = useNavigate();
  // const goDetail = (articleId) => {
  //   navigate(`/detail/${articleId}`)
  // };

  return (
    <RecommendVideoContainer>
      <RecommendVideoTitleContainer>
        <RecommendVideoTitle>최신 영상</RecommendVideoTitle>
        <RecommendVideoTitleLine />
      </RecommendVideoTitleContainer>
      <RecommendVideos >
        <Card/>
      </RecommendVideos>
    </RecommendVideoContainer>
  );
}

