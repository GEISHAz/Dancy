import React, { useState, useRef, useEffect } from "react";
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
  return (
    <FindArea>
      <FindPWTitle>비밀번호 찾기</FindPWTitle>
      <InputArea>
        <InputTitle>Email</InputTitle>
        <FindInput></FindInput>
      </InputArea>
      <SendPinButton>인증번호 전송</SendPinButton>
    </FindArea>
  );
}
