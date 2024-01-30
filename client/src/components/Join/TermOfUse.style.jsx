import { styled } from "styled-components";
// 약관 컨포넌트 스타일

// 약관제목
export const TermTitle = styled.div`
  color: #454545;
  font-family: "NYJ Gothic EB-Regular", Helvetica;
  font-size: 32px;
  font-weight: 400;
  line-height: normal;
`;

// 약관 재사용 컴포넌트
// 소제목
export const TermSubTitle = styled.div`
  color: #454545;
  font-family: "NYJ Gothic B-Regular", Helvetica;
  font-size: 24px;
  font-weight: 400;
  line-height: normal;
`;

// 약관 컨테이너
export const TermContainer = styled.div`
  color: #4a4a4a;
  font-family: "NanumSquareRound", sans-serif;
  font-size: 16px;
  padding: 12px, 36px;
  background-color: #ffffff;
  border: 1px solid black;
  border-radius: 10px;
  height: 199px;
  width: 1218px;
`;

// 약관 체크박스
export const TermCheckBox = styled.div`
  width: 20px;
  height: 20px;
  border: 1px solid black;
`;

// 약관 동의 텍스트
export const TermAgreeText = styled.div`
  font-family: "NYJ Gothic L-regular";
  font-size: 20px;
  font-weight: 400;
  line-height: nomal;
`;

// 전체 동의 텍스트
export const AllAgreeText = styled.div`
  font-family: "NYJ Gothic B-regular";
  font-size: 24px;
  font-weight: 400;
  line-height: nomal;
`;

// 버튼
export const NextStopButton = styled.button`
  width: 120px;
  height: 40px;
  background-color: #f9405e;
  border: 1px solid black;
  border-radius: 3px;
  font-family: "NYJ Gothic EB-regular";
  font-size: 20px;
  color: #ffffff;

  &:hover {
    background-color: #c0354c;
  }
`;
