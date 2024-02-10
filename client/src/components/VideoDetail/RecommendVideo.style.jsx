import styled from "styled-components";

export const RecommendVideoContainer = styled.div`
  width: 1000px;
  z-index: 0;
`;

export const RecommendVideoTitleContainer = styled.div`
  display: flex;
  align-items: center;
`;

export const RecommendVideoTitle = styled.div`
  font-family: 'NanumSquareRound', serif;
  font-size: 24px;
  font-weight: 600;
`;

export const RecommendVideoTitleLine = styled.div`
  border-top: 1px solid lightgray;
  flex: 1;
  margin-left: 4px;
  border-color: #252525;
`;

export const RecommendVideos = styled.div`
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  scale: 1.01;
`;