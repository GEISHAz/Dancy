import React, { useState, useRef, useEffect } from "react";
import axios from "axios";
import * as V from "./VerifyPin.style"
import { publicApi } from "../../util/http-commons";
import NewPwdModal from "./NewPwdModal";


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
        const token = response.data.accessToken;
        localStorage.setItem("token", token);
        setIsOpen(true);
        // return (
        //   { token }
        // );
      })
      .catch(error => {
        const errorType = error.response?.data[0]?.errorType;
        if (errorType === 'VerifyCodeNotMatchException') {
          alert(error.response?.data[0]?.message)
        }
      });
  }

  return (
    <V.FindArea>
      <V.FindPWTitle>인증번호 입력</V.FindPWTitle>
      <V.InputArea>
        <V.PinInputContainer>
          {pin.map((digit, index) => (
            <V.PinInput
              key={index}
              contentEditable
              ref={pinRefs.current[index]}
              onInput={(e) => handlePinChange(index, e.currentTarget.innerText)}
            >
              {digit}
            </V.PinInput>
          ))}
        </V.PinInputContainer>
      </V.InputArea>
      <V.SendPinButton pinFull={isPinFull} onClick={validPin}>비밀번호 변경</V.SendPinButton>
      <NewPwdModal isOpen={isOpen} onClose={() => setModalOpen(false)} />
    </V.FindArea>
  );
}
