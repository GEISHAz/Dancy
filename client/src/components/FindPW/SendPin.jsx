import React, { useState, useRef, useEffect } from "react";
import axios from "axios";
import styled from "styled-components";
import { publicApi } from "../../util/http-commons";
import VerifyPin from "./VerifyPin";

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
  margin-bottom: 41px;
`;

//제목
export const FindPWTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 36px;
  color: black;
  margin-right: 32px;
  margin-bottom: 80px;
`;

// 입력창
export const FindInput = styled.input`
  width: 465px;
  height: 57px;
  border: 1px solid black;
  border-radius: 5px;
  margin-right: 86px;

  &:focus {
    outline: 2px solid #e23e59;
    border: none;
  }
`;

// 인풋 제목
export const InputTitle = styled.div`
  font-family: "NYJ Gothic B";
  font-size: 28px;
  margin-right: 19px;
  color: black;
`;

// 버튼 사이즈
export const SendPinButton = styled.button`
  width: 465px;
  height: 57px;
  background-color: #f9405e;
  border-radius: 5px;
  border: 1px solid black;
  color: white;
  text-align: center;
  font-family: "NYJ Gothic B";
  font-size: 32px;
`;

export default function SendPin() {
  const [targetEmail, setTargetEmail] = useState('');
  const [emailSend, setEmailSend] = useState(false);

  const sendEmail = () => {
    publicApi.post('/email/password/send', { "targetEmail": targetEmail })
      .then(response => {
        alert("인증번호가 전송되었습니다. 이메일을 확인해주세요.");
        setEmailSend(true)
      })
      .catch(error => {
        console.error(error.response);
        const errorType = error.response?.data[0]?.errorType;
        if (errorType === "UserNotFoundException") {
          alert("가입된 이메일이 아닙니다.");
        } else if (errorType === "Email") {
          alert("이메일 형식이 아닙니다.");
        }
      });
  }

  if (emailSend) {
    return <VerifyPin targetEmail={targetEmail} />
  }

  return (
    <FindArea>
      <FindPWTitle>비밀번호 찾기</FindPWTitle>
      <InputArea>
        <InputTitle>Email</InputTitle>
        <FindInput value={targetEmail} onChange={(e) => setTargetEmail(e.target.value)}></FindInput>
      </InputArea>
      <SendPinButton onClick={sendEmail}>인증번호 전송</SendPinButton>
    </FindArea>
  );
}
