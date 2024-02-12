import React, { useState, useRef, useEffect } from "react";
import { pinNumCheck } from "../../api/join";
import { httpStatusCode } from "../../util/http-status";
import * as PM from "./PinModal.style";

const CustomModal = ({ email, isOpen, onClose, onSubmit }) => {
  const [pin, setPin] = useState(["", "", "", "", "", ""]);
  const pinRefs = useRef(pin.map(() => React.createRef()));

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
      return;
    }
  };

  const handleSubmit = async (email) => {
    // 핀 번호가 모두 입력되었는지 확인
    if (pin.every((digit) => digit !== "")) {
      const pinValue = pin.join("");
      console.log("pin:", pinValue);
      console.log("email: ", email);

      // 서버와의 통신 구현 필요 -> 서버 핀번호랑 맞는지 비교후에 200이면 true 반환하는. 로직

      try {
        const response = await pinNumCheck(email, pinValue);
        console.log("response: ", response);
        if (response === httpStatusCode.OK) {
          // 핀넘버랑 동일할 때
          onSubmit(pinValue);
          onClose();
        }
      } catch (error) {
        if (error === httpStatusCode.FOBIDDEN) {
          alert("인증번호가 일치하지 않습니다. 다시 시도해주세요");
        } else if (error === httpStatusCode.NOTFOUND) {
          alert("인증번호가 저장되어 있지 않습니다.");
          onClose();
        } else if (error === httpStatusCode.CONFLICT) {
          alert("이미 가입된 이메일 계정입니다.");
          onClose();
        } else if (error === httpStatusCode.BADREQUEST) {
          alert("이메일 형식이 아닙니다.");
          onClose();
        }
      }
    }
  };

  const handleOverlayClick = (e) => {
    // 모달 배경 클릭 시 모달을 닫음
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  useEffect(() => {
    // 모달이 열릴 때 첫 번째 div로 포커스 이동
    if (isOpen) {
      pinRefs.current[0].current.focus();
    }
  }, [isOpen]);

  useEffect(() => {
    // 모달이 닫힐 때 pin 상태 초기화
    if (!isOpen) {
      setPin(["", "", "", "", "", ""]);
    }
  }, [isOpen]);

  return (
    <PM.ModalOverlay isOpen={isOpen} onClick={handleOverlayClick}>
      <PM.ModalContainer>
        <PM.ModalContent>
          <PM.ModalTitle>인증번호 입력</PM.ModalTitle>
          <PM.PinInputContainer>
            {pin.map((digit, index) => (
              <PM.PinInput
                key={index}
                contentEditable
                ref={pinRefs.current[index]}
                onInput={(e) => handlePinChange(index, e.currentTarget.innerText)}
              >
                {digit}
              </PM.PinInput>
            ))}
          </PM.PinInputContainer>
        </PM.ModalContent>
        <PM.ModalButtonContainer>
          <PM.ModalButton onClick={() => handleSubmit(email)}>인증 완료</PM.ModalButton>
        </PM.ModalButtonContainer>
      </PM.ModalContainer>
    </PM.ModalOverlay>
  );
};

export default CustomModal;
