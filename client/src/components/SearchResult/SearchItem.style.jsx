import styled from "styled-components";

// 검색 썸네일
export const SearchThumbNail = styled.img`
  width: 300px;
  height: 200px;
  border-radius: 10px;
  border: 1px solid black;
`;

// 영상 제목
export const SearchResultTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 16px;
  color: #434343;
`;

// 조회수 및 날짜
export const SearchResultCount = styled.div`
  font-family: "NanumSquareRound";
  font-size: 8px;
  color: #6c6c6c;
`;

// 내용
export const SearchResultContent = styled.div`
  font-family: "NanumSquareRound";
  font-size: 8px;
  color: #434343;
`;

// 유저 프로필
export const SearchResultProfile = styled.img`
  width: 24px;
  height: 24px;
  border-radius: 50%;
`;

// 유저이름
export const SearchResultAuthor = styled.div`
  font-family: "NanumSquareRound-Bold";
  font-size: 12px;
  color: #434343;
`;
