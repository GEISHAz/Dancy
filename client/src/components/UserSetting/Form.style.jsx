import { styled } from "styled-components";

// 전체 폼 구성
export const JoinFormArea = styled.div`
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 16px;
`;

// 필수 항목 공지
export const NoticeArea = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  margin-bottom: 12px;
`;

// 폼 매 줄 마다 설정
export const FormDetailArea = styled.div`
  width: 100%;
  display: flex;
  justify-content: start;
  align-items: center;
  margin-top: 1rem;
  gap: 20px;
`;

// 텍스트 엔터 처리
export const EnterArea = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: start;
  margin-right: ${(props) => props.margin || "0px"};
`;

export const InputContainer = styled.div`
  position: relative;
`;

// 라디오 버튼 커스텀
export const RadioContainer = styled.div`
  display: flex;
  font-family: "NYJ Gothic B";
  font-size: 16px;
  gap: 12px;
  margin-right: ${(props) => props.margin || "0px"};

  input {
    accent-color: #f9405e;
  }
`;
