import React, { useState, useRef, useEffect } from "react";
import axios from "axios";
import * as S from "./SendPin.style";
import { publicApi } from "../../util/http-commons";
import VerifyPin from "./VerifyPin";


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
    <S.FindArea>
      <S.FindPWTitle>비밀번호 찾기</S.FindPWTitle>
      <S.InputArea>
        <S.InputTitle>Email</S.InputTitle>
        <S.FindInput value={targetEmail} onChange={(e) => setTargetEmail(e.target.value)}></S.FindInput>
      </S.InputArea>
      <S.SendPinButton onClick={sendEmail}>인증번호 전송</S.SendPinButton>
    </S.FindArea>
  );
}
