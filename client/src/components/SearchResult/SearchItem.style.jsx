import styled from "styled-components";


export const VideoListArea = styled.div`
  width: 772px;
  height: 770px;
  display: flex;
  flex-direction: column;
  margin: 20px;
`;

export const NoResultMessage = styled.div`
  font-size: 20px;
  color: #888;
  text-align: center;
  padding-top: 50px;
`

// 검색 결과 컨테이너
export const VideoContainer = styled.div`
  display: flex;
  flex-direction: row;
  margin: 15px 0px;
  gap: 36px;
`

// 검색 썸네일
export const SearchThumbNail = styled.img`
  width: 300px;
  height: 200px;
  border-radius: 10px;
  border: 1px solid black;
`;

// 검색결과 내용 구역
export const VideoContentArea = styled.div`
  width: 434px;
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: start;
  gap: 30px;
`;

// 제목 + 조회수 & 날짜
export const SearchResultCount = styled.div`
  display: flex;
  flex-direction: column;
  font-family: "NanumSquareRound";
  font-size: 12px;
  color: lightgray;
  gap: 8px;
`;

// 영상 제목
export const SearchResultTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 24px;
  color: #252525;
`;

// 조회수 및 날짜
export const SearchViewCreated = styled.div`
  font-family: "NanumSquareRound";
  font-size: 12px;
  color: gray;
`

// // 내용
// export const SearchResultContent = styled.div`
//   font-family: "NanumSquareRound";
//   font-size: 8px;
//   color: #434343;
//   margin-bottom: 20px;
// `;

// 유저 프로필
export const SearchResultProfile = styled.img`
  width: 32px;
  height: 32px;
  border-radius: 50%;
  border: 1px solid #252525;
`;

// 유저이름
export const SearchResultAuthor = styled.div`
  font-family: "NanumSquareRound-Bold";
  font-size: 20px;
  color: #434343;
`;



export const UserContentArea = styled.div`
  height: 24px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
`;