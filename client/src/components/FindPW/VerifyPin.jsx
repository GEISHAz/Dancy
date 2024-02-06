import React, { useState, useRef, useEffect } from "react";
import axios from "axios";
import styled from "styled-components";
import { publicApi } from "../../util/http-commons";
import NewPwdModal from "./NewPwdModal";

// 전체 화면 구성
const FindArea = styled.div`
  width: 100%;
  height: 680px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

const InputArea = styled.div`
  display: flex;
  align-items: center;
`;

// 제목
const FindPWTitle = styled.div`
  font-family: "NYJ Gothic L";
  font-size: 36px;
  color: black;
  margin-bottom: 48px;
`;

// 인증번호 입력 부분
const PinInputContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 58px;
`;

// 번호 입력 번호
const PinInput = styled.div`
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
const SendPinButton = styled.button`
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

export default function VerifyPin({targetEmail}) {
  const [pin, setPin] = useState(["", "", "", "", "", ""]);
  const pinRefs = useRef(pin.map(() => React.createRef()));
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    // 컴포넌트가 마운트될 때 첫 번째 입력란에 포커스를 줌
    pinRefs.current[0].current.focus();
  }, []); // 빈 배열을 전달하여 한 번만 실행되도록 설정

  const handlePinChange = (index, value) => {
    const newPin = [...pin];
    newPin[index] = value;
    setPin(newPin);

    if (value !== "" && index < pin.length - 1) {
      // 입력이 있고, 마지막 자리가 아니면 다음 ref로 포커스 이동
      pinRefs.current[index + 1].current.focus();
    }

    if (value !== "" && index === pin.length - 1) {
      // 마지막 자리에 도달하면 focus를 해제
      pinRefs.current[index].current.blur();
    }
  };

  // 핀번호가 다 차면... true 하나라도 차지 않으면 false
  const isPinFull = pin.every((digit) => digit !== "");

  const validPin = () => {
    const verifyCode = pin.join('');
    publicApi.post('/auth/password/check', { "targetEmail": targetEmail, "verifyCode": verifyCode })
      .then(response => {
        alert("인증에 성공하였습니다.")
        setIsOpen(true);
      })
      .catch(error => {
        const errorType = error.response?.data[0]?.errorType;
        if (errorType === 'VerifyCodeNotMatchException') {
          alert(error.response?.data[0]?.message)
        }
      });
  }

  return (
    <FindArea>
      <FindPWTitle>인증번호 입력</FindPWTitle>
      <InputArea>
        <PinInputContainer>
          {pin.map((digit, index) => (
            <PinInput
              key={index}
              contentEditable
              ref={pinRefs.current[index]}
              onInput={(e) => handlePinChange(index, e.currentTarget.innerText)}
            >
              {digit}
            </PinInput>
          ))}
        </PinInputContainer>
      </InputArea>
      <SendPinButton pinFull={isPinFull} onClick={validPin}>비밀번호 변경</SendPinButton>
      <NewPwdModal isOpen={isOpen} onClose={() => setModalOpen(false)} />
    </FindArea>
  );
}
