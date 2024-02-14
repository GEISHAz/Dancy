import styled from "styled-components";

// 전체 화면 구성
export const FindArea = styled.div`
  width: 100%;
  height: 680px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

export const InputArea = styled.div`
  display: flex;
  align-items: center;
`;

// 제목
export const FindPWTitle = styled.div`
  font-family: "NYJ Gothic L";
  font-size: 36px;
  color: black;
  margin-bottom: 48px;
`;

// 인증번호 입력 부분
export const PinInputContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 58px;
`;

// 번호 입력 번호
export const PinInput = styled.div`
  width: 65px;
  height: 75px;
  margin: 0 9px;
  text-align: center;
  font-family: "NYJ Gothic B";
  font-size: 48px;
  border: 1px solid black;
  border-radius: 5px;
  background-color: white;
  outline: none;
`;

// 버튼
export const SendPinButton = styled.button`
  width: 480px;
  height: 62px;
  border-radius: 5px;
  border: 1px solid black;
  color: white;
  text-align: center;
  font-family: "NYJ Gothic B";
  font-size: 32px;
  background-color: ${(props) => (props.pinFull ? "#F9405E" : "#DFDFDF")};
  cursor: ${(props) => (props.pinFull ? "pointer" : "not-allowed")};
`;