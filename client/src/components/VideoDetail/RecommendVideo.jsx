import React from "react";
import Card from '../Stage/Card';
import { useNavigate } from "react-router-dom";
import * as RV from "./RecommendVideo.style"


export default function RecommendVideo() {
  // const navigate = useNavigate();
  // const goDetail = (articleId) => {
  //   navigate(`/detail/${articleId}`)
  // };

  return (
    <RV.RecommendVideoContainer>
      <RV.RecommendVideoTitleContainer>
        <RV.RecommendVideoTitle>최신 영상</RV.RecommendVideoTitle>
        <RV.RecommendVideoTitleLine />
      </RV.RecommendVideoTitleContainer>
      <RV.RecommendVideos >
        <Card/>
      </RV.RecommendVideos>
    </RV.RecommendVideoContainer>
  );
}

