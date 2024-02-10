import React from "react";
import * as SI from "./SearchItem.style";
import styled from "styled-components";
import { Link } from "react-router-dom";

export const VideoListArea = styled.div`
  width: 772px;
  height: 800px;
  display: flex;
  flex-direction: column;
`;


export const VideoContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
`

export const VideoContentArea = styled.div`
  width: 434px;
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: start;
  gap: 10px;
`;

export const UserContentArea = styled.div`
  height: 24px;
  display: flex;
  gap: 7px;
  align-items: center;
`;

const VideoData = [
  {
    videoTitle: "첫영상입니다요",
    thumbnailImageUrl: "/src/assets/thumbnail.png",
    videoContent: "이번에 첫 영상을 올리는데 많은 관심부탁드립니다.",
    videoCount: "100",
    videoUserImg: "/src/assets/profileimage.png",
    videoUserName: "MEOW",
  },
  // 동일한 데이터 6번 추가
  ...Array(5).fill({
    videoTitle: "첫영상입니다요",
    thumbnailImageUrl: "/src/assets/thumbnail.png",
    videoContent: "이번에 첫 영상을 올리는데 많은 관심부탁드립니다.",
    videoCount: "100",
    videoUserImg: "/src/assets/profileimage.png",
    videoUserName: "MEOW",
  }),
];

export default function SearchItem({
  videoTitle,
  thumbnailImageUrl,
  videoContent,
  videoCount,
  videoUserImg,
  videoUserName,
}) {
  return (
    <VideoListArea>
      {VideoData.map((video, index) => (
        <Link to={`/detail/${index}`} key={index}>
          <div key={index}>
            <VideoContainer>
            <SI.SearchThumbNail src={video.thumbnailImageUrl}></SI.SearchThumbNail>
            <VideoContentArea>
              <SI.SearchResultTitle>{video.videoTitle}</SI.SearchResultTitle>
              <SI.SearchResultCount>
                조회수 {video.videoCount}회 | 2024.01.03
              </SI.SearchResultCount>
              <SI.SearchResultContent>{video.videoContent}</SI.SearchResultContent>
              <UserContentArea>
                <SI.SearchResultProfile src={video.videoUserImg}></SI.SearchResultProfile>
                <SI.SearchResultAuthor>@{video.videoUserName}님의 비디오</SI.SearchResultAuthor>
              </UserContentArea>
            </VideoContentArea>
            </VideoContainer>
          </div>
        </Link>
      ))}
    </VideoListArea>
  );
}
