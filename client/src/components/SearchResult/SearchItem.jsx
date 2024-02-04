import * as SI from "./SearchItem.style";
import styled from "styled-components";

export const VideoListArea = styled.div`
  width: 772px;
  height: 200;
  display: flex;
`;

export const VideoContentArea = styled.div`
  width: 434px;
  height: 138px;
  display: flex;
  flex-direction: column;
`;

export const UserContentArea = styled.div`
  height: 24px;
  display: flex;
  gap: 7px;
`;

// 날짜 데이터도 추가로 만져줘야할듯여.
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
      <SI.SearchThumbNail src={thumbnailImageUrl}></SI.SearchThumbNail>
      <VideoContentArea>
        <SI.SearchResultTitle>{videoTitle}</SI.SearchResultTitle>
        <SI.SearchResultCount>조회수 {videoCount}회 | 2024.01.03</SI.SearchResultCount>
        <SI.SearchResultContent>{videoContent}</SI.SearchResultContent>
        <UserContentArea>
          <SI.SearchResultProfile src={videoUserImg}></SI.SearchResultProfile>
          <SI.SearchResultAuthor>{videoUserName}님의 비디오</SI.SearchResultAuthor>
        </UserContentArea>
      </VideoContentArea>
    </VideoListArea>
  );
}
